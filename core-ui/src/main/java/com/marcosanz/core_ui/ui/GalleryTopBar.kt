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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.marcosanz.core_ui.R
import com.marcosanz.core_ui.theme.PreviewScaffold
import com.marcosanz.core_ui.theme.defaults.AppColor
import com.marcosanz.core_ui.util.Constant
import kotlin.math.roundToInt


@Preview
@Composable
private fun GalleryTopBarPreview() {
    PreviewScaffold {
        GalleryTopBar(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun GalleryTopBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    isRotateButtonVisible: Boolean = true,
    isDownloadButtonVisible: Boolean = true,
    isShareButtonVisible: Boolean = true,
    onBack: () -> Unit = {},
    onRotate: () -> Unit = {},
    onDownload: () -> Unit = {},
    onShare: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

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

            // 🔄 Rotate
            GalleryRotateButton(
                isVisible = isRotateButtonVisible,
                onClick = onRotate
            )
            // 🧮 More options
            Box {
                GalleryMoreOptionsButton(
                    isVisible = isDownloadButtonVisible || isShareButtonVisible
                ) {
                    expanded = true
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    // Download
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.gallery_dialog_cd_download_button)) },
                        onClick = {
                            expanded = false
                            onDownload()
                        }
                    )
                    // Share
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.gallery_dialog_cd_share_button)) },
                        onClick = {
                            expanded = false
                            onShare()
                        }
                    )
                }
            }
        }
    }

}