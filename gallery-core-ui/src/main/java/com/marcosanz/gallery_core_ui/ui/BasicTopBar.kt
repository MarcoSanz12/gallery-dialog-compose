package com.marcosanz.gallery_core_ui.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.theme.defaults.AppColor
import com.marcosanz.gallery_core_ui.util.Constant
import kotlin.math.roundToInt

@Preview
@Composable
private fun BasicTopBarPreview() {
    PreviewScaffold {
        BasicTopBar()
    }
}

@Composable
fun BasicTopBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    onBack: () -> Unit = {},
    buttonsContent: @Composable RowScope.() -> Unit = {}
) {
    val slideAnimationSpec: FiniteAnimationSpec<IntOffset> = remember {
        tween(durationMillis = Constant.UI_VISIBILITY_ANIM_DURATION_MS)
    }
    val fadeAnimationSpec: FiniteAnimationSpec<Float> = remember {
        tween(durationMillis = (Constant.UI_VISIBILITY_ANIM_DURATION_MS * 0.5f).roundToInt())
    }

    AnimatedVisibility(
        modifier = modifier,
        enter =
            fadeIn(
                animationSpec = fadeAnimationSpec
            ) + slideInVertically(
                initialOffsetY = {
                    -it
                },
                animationSpec = slideAnimationSpec
            ),
        exit =
            fadeOut(
                animationSpec = fadeAnimationSpec
            ) + slideOutVertically(
                targetOffsetY = {
                    -it
                },
                animationSpec = slideAnimationSpec
            ),
        visible = isVisible
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppColor.blackTransparent)
                .statusBarsPadding(),
        ) {
            // 🔙 Back button
            GalleryBackButton(onClick = onBack)

            Spacer(modifier = Modifier.weight(1f))
            buttonsContent()
        }
    }
}