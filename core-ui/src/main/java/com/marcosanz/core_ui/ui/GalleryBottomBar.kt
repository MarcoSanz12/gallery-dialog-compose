package com.marcosanz.core_ui.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.marcosanz.core_ui.theme.PreviewScaffold
import com.marcosanz.core_ui.theme.defaults.AppColor
import com.marcosanz.core_ui.theme.defaults.AppPadding
import com.marcosanz.core_ui.util.Constant
import kotlin.math.abs

@Preview
@Composable
private fun GalleryBottomBarPreview() {
    PreviewScaffold {
        val texts = listOf("Example description")
        GalleryBottomBar(
            pagerState = rememberPagerState(pageCount = { texts.size }),
            texts = texts
        )
    }
}

@Composable
fun GalleryBottomBar(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    texts: List<String?> = emptyList()
) {
    val slideAnimationSpec: FiniteAnimationSpec<IntOffset> = remember {
        tween(durationMillis = Constant.UI_VISIBILITY_ANIM_DURATION_MS)
    }
    val fadeAnimationSpec: FiniteAnimationSpec<Float> = remember {
        tween(durationMillis = Constant.UI_VISIBILITY_ANIM_DURATION_MS)
    }

    // Aniamted background opacitiy (if text is empty, it is not visible)
    val backgroundAlphaState by remember(pagerState, texts) {
        derivedStateOf {
            (pagerState.currentPage - 1..pagerState.currentPage + 1).sumOf { page ->
                if (page in texts.indices && !texts[page].isNullOrBlank()) {
                    val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                    (1f - abs(pageOffset)).coerceIn(0f, 1f).toDouble()
                } else 0.0
            }.toFloat().coerceIn(0f, 1f)
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = fadeIn(fadeAnimationSpec) + slideInVertically(slideAnimationSpec) { it },
        exit = fadeOut(fadeAnimationSpec) + slideOutVertically(slideAnimationSpec) { it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    alpha = backgroundAlphaState
                }
                .background(color = AppColor.blackTransparent)
                .padding(horizontal = AppPadding.extraLarge)
                .padding(top = AppPadding.medium, bottom = AppPadding.small)
                .navigationBarsPadding()
        ) {
            val currentPage = pagerState.currentPage
            for (page in (currentPage - 1)..(currentPage + 1)) {
                if (page in texts.indices) {
                    val text = texts[page]
                    if (!text.isNullOrBlank()) {
                        Text(
                            text = text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer {
                                    val offset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                                    
                                    alpha = (1f - abs(offset) * 2).coerceIn(0f, 1f)
                                    translationY = abs(offset) * size.height * 0.5f
                                },
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                            minLines = 3,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}
