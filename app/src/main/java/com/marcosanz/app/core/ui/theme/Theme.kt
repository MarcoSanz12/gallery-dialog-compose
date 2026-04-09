package com.marcosanz.app.core.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.marcosanz.app.core.ui.theme.defaults.AppColor
import com.marcosanz.app.core.ui.theme.defaults.AppCornerRadius

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTheme(
    content: @Composable() () -> Unit
) {
    CompositionLocalProvider(LocalRippleConfiguration provides BaseRippleConfiguration) {
        MaterialTheme(
            colorScheme = AppLightScheme,
            shapes = AppShapes,
            typography = AppTypography,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private val BaseRippleConfiguration = RippleConfiguration(color = AppColor.ripple)

private val AppLightScheme = lightColorScheme()
/*private val AppLightScheme = lightColorScheme(
    primary = AppColor.Turquoise.turquoise,
    onPrimary = AppColor.Green.green,
    primaryContainer = AppColor.Green.green,
    onPrimaryContainer = AppColor.Green.green950,
    secondary = AppColor.Turquoise.turquoise,
    onTertiary = AppColor.Green.green950,
    onTertiaryContainer = AppColor.Green.green950,
    onSecondary = AppColor.Green.green,
    secondaryContainer = AppColor.Turquoise.turquoise,
    onSecondaryContainer = AppColor.Green.green,
    error = AppColor.Sentiment.redNegative500,
    onError = Color.White,
    errorContainer = AppColor.Sentiment.redNegative500,
    onErrorContainer = Color.White,
    background = Color.White,
    onBackground = AppColor.Neutral.neutral950,
    surface = AppColor.Green.green,
    onSurface = Color.White,
)*/

private val AppShapes = Shapes(
    extraSmall = AppCornerRadius.smallShape,
    small = AppCornerRadius.smallShape,
    medium = AppCornerRadius.mediumShape,
    large = AppCornerRadius.largeShape,
    extraLarge = AppCornerRadius.extraLargeShape
)

