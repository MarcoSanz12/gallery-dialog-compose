package com.marcosanz.gallery_core_ui.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
    checked: Boolean = false,
    checkable: Boolean = false,
    contentDescription: String? = null,
    iconScale: Float = GalleryButtonDefaults.ICON_SCALE,
    colors: ButtonColors = GalleryButtonDefaults.buttonColors,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring()
    )

    val animatedColors by animateColorsAsState(
        colors = colors,
        checked = checked,
        checkable = checkable
    )

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
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
            enabled = enabled,
            shape = CircleShape,
            interactionSource = interactionSource,
            contentPadding = PaddingValues.Zero,
            colors = animatedColors,
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
        contentDescription = stringResource(R.string.gallery_dialog_core_ui_cd_rotate_button),
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
        contentDescription = stringResource(R.string.gallery_dialog_core_ui_cd_back_button),
        onClick = onClick
    )
}

@Composable
private fun animateColorsAsState(
    colors: ButtonColors,
    checked: Boolean,
    checkable: Boolean
): State<ButtonColors> {
    if (!checkable) return remember(colors) { mutableStateOf(colors) }

    val containerColor = animateColorAsState(
        targetValue = if (checked) colors.containerColor else colors.disabledContainerColor,
        animationSpec = tween(durationMillis = 300),
        label = "ButtonContainerColor"
    )

    val contentColor = animateColorAsState(
        targetValue = if (checked) colors.contentColor else colors.disabledContentColor,
        animationSpec = tween(durationMillis = 300),
        label = "ButtonContentColor"
    )

    return remember(colors) {
        derivedStateOf {
            ButtonColors(
                containerColor = containerColor.value,
                contentColor = contentColor.value,
                disabledContainerColor = colors.disabledContainerColor,
                disabledContentColor = colors.disabledContentColor
            )
        }
    }
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