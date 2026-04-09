package com.marcosanz.app.core.ui.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.marcosanz.app.core.ui.component.common.LocalButtonBasicColors
import com.marcosanz.app.core.ui.component.common.PreviewScaffold
import com.marcosanz.app.core.ui.theme.defaults.AppColor
import com.marcosanz.app.core.ui.theme.defaults.AppCornerRadius
import com.marcosanz.app.core.ui.theme.defaults.AppPadding
import com.marcosanz.app.core.ui.theme.defaults.AppTextStyle

private typealias ComposeColor = Color

@Composable
fun AppButtonBasic(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: ButtonBasicSize = ButtonBasicSize.LARGE,
    shape: RoundedCornerShape = AppCornerRadius.largeShape,
    contentDescription: String? = null,
    maxLines: Int = 1,
    iconSize: Dp = ButtonBasicDefaults.iconSize,
    colors: ButtonBasicColors = LocalButtonBasicColors.current,
    outlined: Boolean = false,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    loading: Boolean = false,
    onClick: () -> Unit
) {

    val borderWidth = remember(outlined) {
        if (outlined) 1.dp else 0.dp
    }
    ButtonBasic(
        text = text,
        modifier = modifier,
        enabled = enabled,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        loading = loading,
        contentDescription = contentDescription,
        contentPadding = size.contentPadding,
        iconSize = iconSize,
        maxLines = maxLines,
        shape = shape,
        textStyle = size.textStyle,
        colors = colors,
        borderWidth = borderWidth,
        onClick = onClick
    )

}


@Composable
private fun ButtonBasic(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    loading: Boolean = false,
    contentDescription: String? = null,
    contentPadding: PaddingValues = ButtonBasicSize.LARGE.contentPadding,
    maxLines: Int = 1,
    iconSize: Dp = ButtonBasicDefaults.iconSize,
    textStyle: TextStyle = AppTextStyle.Button.button,
    shape: RoundedCornerShape = AppCornerRadius.largeShape,
    colors: ButtonBasicColors = LocalButtonBasicColors.current,
    borderWidth: Dp = 0.dp,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring()
    )

    val animatedTextColor by animateTextColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        colors = colors
    )

    val animatedContainerColor by animateContainerColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        colors = colors
    )

    val animatedLeadingIconColor by animateLeadingIconColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        colors = colors
    )

    val animatedTrailingIconColor by animateTrailingIconColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        colors = colors
    )

    val animatedBorderColor by animateBorderColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        colors = colors
    )

    val borderStroke: BorderStroke? = remember(borderWidth, animatedBorderColor) {
        if (borderWidth == 0.dp)
            null
        else
            BorderStroke(width = borderWidth, color = animatedBorderColor)

    }

    val buttonColor = remember(animatedTextColor, animatedContainerColor) {
        ButtonColors(
            containerColor = animatedContainerColor,
            contentColor = animatedTextColor,
            disabledContainerColor = animatedContainerColor,
            disabledContentColor = animatedTextColor
        )
    }


    Button(
        modifier = modifier
            .scale(scale)
            .semantics(true) {
                if (contentDescription != null)
                    this.contentDescription = contentDescription
            }
            .width(IntrinsicSize.Max),
        enabled = enabled,
        onClick = onClick,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        border = borderStroke,
        colors = buttonColor,
        shape = shape
    ) {
        // Leading Icon
        ButtonBasicIcon(
            modifier = Modifier.padding(end = AppPadding.small),
            iconSize = iconSize,
            icon = leadingIcon,
            loading = loading,
            color = animatedLeadingIconColor
        )
        // Texto
        if (text.isNotBlank())
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = text,
                color = LocalContentColor.current,
                style = textStyle,
                maxLines = maxLines
            )
        // Trailing Icon
        if (trailingIcon != null)
            ButtonBasicIcon(
                modifier = Modifier.padding(start = AppPadding.small),
                iconSize = iconSize,
                icon = trailingIcon,
                color = animatedTrailingIconColor
            )
    }
}

@Composable
private fun ButtonBasicIcon(
    iconSize: Dp,
    color: Color,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    loading: Boolean = false
) {
    if (icon == null && !loading)
        return

    Box(
        modifier = modifier
            .size(iconSize),
        contentAlignment = Alignment.Center
    ) {
        if (loading)
            CircularProgressIndicator(
                color = color,
                trackColor = Color.Transparent,
            )
        else if (icon != null)
            Image(
                painter = icon,
                colorFilter =
                    if (color.alpha != 0f)
                        ColorFilter.tint(color = color)
                    else
                        null,
                contentDescription = null
            )
    }
}

@Composable
private fun animateBasicColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    pressedColor: Color,
    enabledColor: Color,
    disabledColor: Color,
    label: String = "colorAnimation",
    animationDuration: Int = ButtonBasicDefaults.COLOR_ENABLE_ANIMATION_DURATION
): State<Color> = animateColorAsState(
    targetValue = when {
        isPressed -> pressedColor
        enabled -> enabledColor
        else -> disabledColor
    },
    label = label,
    animationSpec = tween(animationDuration)
)


@Composable
private fun animateContainerColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    colors: ButtonBasicColors,
): State<Color> =
    animateBasicColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        pressedColor = colors.pressedContainerColor,
        enabledColor = colors.containerColor,
        disabledColor = colors.disabledContainerColor,
        label = "containerColorAnimation"
    )

@Composable
private fun animateTextColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    colors: ButtonBasicColors
): State<Color> =
    animateBasicColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        pressedColor = colors.pressedTextColor,
        enabledColor = colors.textColor,
        disabledColor = colors.disabledTextColor,
        label = "textColorAnimation"
    )

@Composable
fun animateLeadingIconColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    colors: ButtonBasicColors
): State<Color> =
    animateBasicColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        pressedColor = colors.pressedLeadingIconColor,
        enabledColor = colors.leadingIconColor,
        disabledColor = colors.disabledLeadingIconColor,
        label = "leadingIconColorAnimation"
    )

@Composable
fun animateTrailingIconColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    colors: ButtonBasicColors
): State<Color> =
    animateBasicColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        pressedColor = colors.pressedTrailingIconColor,
        enabledColor = colors.trailingIconColor,
        disabledColor = colors.disabledTrailingIconColor,
        label = "trailingIconColorAnimation"
    )

@Composable
fun animateBorderColorAsState(
    isPressed: Boolean,
    enabled: Boolean,
    colors: ButtonBasicColors
): State<Color> =
    animateBasicColorAsState(
        isPressed = isPressed,
        enabled = enabled,
        pressedColor = colors.pressedBorderColor,
        enabledColor = colors.borderColor,
        disabledColor = colors.disabledBorderColor,
        label = "borderColorAnimation"
    )

data class ButtonBasicColors(
    // Text
    val textColor: Color,
    val pressedTextColor: Color,
    val disabledTextColor: Color,

    // Container
    val containerColor: Color,
    val pressedContainerColor: Color,
    val disabledContainerColor: Color,

    // Leading
    val leadingIconColor: Color,
    val pressedLeadingIconColor: Color,
    val disabledLeadingIconColor: Color,


    // Trailing
    val trailingIconColor: Color,
    val pressedTrailingIconColor: Color,
    val disabledTrailingIconColor: Color,


    // Border
    val borderColor: Color,
    val pressedBorderColor: Color,
    val disabledBorderColor: Color
) {

    constructor(contentColor: Color, containerColor: Color) : this(
        textColor = contentColor,
        pressedTextColor = contentColor,
        disabledTextColor = AppColor.disabledText,

        containerColor = containerColor,
        pressedContainerColor = containerColor,
        disabledContainerColor = AppColor.disabledContainer,

        leadingIconColor = contentColor,
        pressedLeadingIconColor = contentColor,
        disabledLeadingIconColor = AppColor.disabledText,

        trailingIconColor = contentColor,
        pressedTrailingIconColor = contentColor,
        disabledTrailingIconColor = AppColor.disabledText,

        borderColor = ComposeColor.Transparent,
        pressedBorderColor = ComposeColor.Transparent,
        disabledBorderColor = ComposeColor.Transparent
    )

    constructor(contentColor: Color, containerColor: Color, borderColor: Color) : this(
        textColor = contentColor,
        pressedTextColor = contentColor,
        disabledTextColor = AppColor.disabledText,

        containerColor = containerColor,
        pressedContainerColor = containerColor,
        disabledContainerColor = AppColor.disabledContainer,

        leadingIconColor = contentColor,
        pressedLeadingIconColor = contentColor,
        disabledLeadingIconColor = AppColor.disabledText,

        trailingIconColor = contentColor,
        pressedTrailingIconColor = contentColor,
        disabledTrailingIconColor = AppColor.disabledText,

        borderColor = borderColor,
        pressedBorderColor = borderColor,
        disabledBorderColor = AppColor.disabledText
    )

    constructor(
        contentColor: Color,
        pressedContentColor: Color,
        containerColor: Color,
        pressedContainerColor: Color,
        borderColor: Color = ComposeColor.Transparent,
        pressedBorderColor: Color = ComposeColor.Transparent,
        disabledBorderColor: Color = ComposeColor.Transparent
    ) : this(
        textColor = contentColor,
        pressedTextColor = pressedContentColor,
        disabledTextColor = AppColor.disabledText,

        containerColor = containerColor,
        pressedContainerColor = pressedContainerColor,
        disabledContainerColor = AppColor.disabledContainer,

        leadingIconColor = contentColor,
        pressedLeadingIconColor = contentColor,
        disabledLeadingIconColor = AppColor.disabledText,

        trailingIconColor = contentColor,
        pressedTrailingIconColor = contentColor,
        disabledTrailingIconColor = AppColor.disabledText,

        borderColor = borderColor,
        pressedBorderColor = pressedContentColor,
        disabledBorderColor = disabledBorderColor
    )

    fun withoutIconColor() = copy(
        leadingIconColor = ComposeColor.Transparent,
        pressedLeadingIconColor = ComposeColor.Transparent,
        trailingIconColor = ComposeColor.Transparent,
        pressedTrailingIconColor = ComposeColor.Transparent
    )
}

enum class ButtonBasicSize(val contentPadding: PaddingValues, val textStyle: TextStyle) {

    EXTRA_LARGE(
        contentPadding = PaddingValues(
            horizontal = AppPadding.medium,
            vertical = AppPadding.large
        ),
        textStyle = AppTextStyle.Button.button
    ),
    LARGE(
        contentPadding = PaddingValues(
            horizontal = AppPadding.medium,
            vertical = AppPadding.large - 4.dp
        ),
        textStyle = AppTextStyle.Button.button
    ),
    MEDIUM(
        contentPadding = PaddingValues(
            horizontal = AppPadding.medium,
            vertical = AppPadding.small
        ),
        textStyle = AppTextStyle.Button.button
    ),
    SMALL(
        contentPadding = PaddingValues(
            horizontal = AppPadding.medium,
            vertical = AppPadding.small
        ),
        textStyle = AppTextStyle.Button.button
    )
}


@Preview
@Composable
private fun ButtonBasicPreview() {
    PreviewScaffold {
        Column(
            modifier = Modifier.padding(AppPadding.medium),
            verticalArrangement = Arrangement.spacedBy(AppPadding.medium)
        ) {
        }
    }
}

object ButtonBasicDefaults {

    object Color {
        private val defaultTextColor =
            ComposeColor.Black
        private val defaultPressedTextColor =
            ComposeColor.White

        object Primary {
            val default =
                ButtonBasicColors(
                    contentColor = defaultTextColor,
                    pressedContentColor = defaultPressedTextColor,
                    containerColor = ComposeColor.White,
                    pressedContainerColor = ComposeColor.Black
                )
        }

        object Secondary {

            val default =
                ButtonBasicColors(
                    contentColor = defaultTextColor,
                    pressedContentColor = defaultPressedTextColor,
                    containerColor = ComposeColor.White,
                    pressedContainerColor = ComposeColor.Black
                )
        }
    }

    val iconSize = 16.dp

    const val COLOR_ENABLE_ANIMATION_DURATION = 200

}