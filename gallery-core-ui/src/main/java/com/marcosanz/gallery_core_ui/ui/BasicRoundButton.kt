package com.marcosanz.gallery_core_ui.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcosanz.gallery_core_ui.R
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.theme.defaults.AppColor

@Preview
@Composable
private fun BasicRoundButtonPreview() {
    PreviewScaffold(backgroundColor = Color.White) {
        // Back
        Text(text = "Back button")
        GalleryBackButton() { }

        // Rotate
        Text(text = "Rotate button")
        GalleryRotateButton() { }

    }
}

@Composable
fun BasicRoundButton(
    icon: Painter,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    enabled: Boolean = true,
    contentDescription: String? = null,
    iconScale: Float = GalleryButtonDefaults.ICON_SCALE,
    colors: ButtonColors = GalleryButtonDefaults.buttonColors,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    AnimatedVisibility(
        modifier = modifier
            .size(48.dp)
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick
            )
            .padding(6.dp),
        visible = isVisible
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxSize(),
            enabled = enabled,
            shape = CircleShape,
            interactionSource = interactionSource,
            contentPadding = PaddingValues.Zero,
            colors = colors,
            content = {
                Icon(
                    modifier = Modifier.graphicsLayer {
                        scaleX = iconScale
                        scaleY = iconScale
                    },
                    painter = icon,
                    contentDescription = contentDescription
                )
            }
        )
    }

}

@Composable
fun GalleryRotateButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundButton(
        modifier = modifier,
        isVisible = isVisible,
        icon = painterResource(R.drawable.ic_gallery_dialog_rotate),
        contentDescription = stringResource(R.string.gallery_dialog_cd_rotate_button),
        iconScale = 0.9f,
        onClick = onClick
    )
}

@Composable
fun GalleryBackButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundButton(
        modifier = modifier,
        isVisible = isVisible,
        icon = painterResource(R.drawable.ic_gallery_dialog_arrow_back),
        contentDescription = stringResource(R.string.gallery_dialog_cd_back_button),
        onClick = onClick
    )
}




object GalleryButtonDefaults {
    val buttonColors
        @Composable get() = ButtonColors(
            containerColor = AppColor.blackDarkerTransparent,
            contentColor = AppColor.white,
            disabledContainerColor = AppColor.blackDarkerTransparent,
            disabledContentColor = AppColor.disabledContent
        )
    const val ICON_SCALE: Float = 1.1f
}