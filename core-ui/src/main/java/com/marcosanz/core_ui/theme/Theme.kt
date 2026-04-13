package com.marcosanz.core_ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.marcosanz.core_ui.extensions.copyForTheme
import com.marcosanz.core_ui.theme.defaults.AppCornerRadius
import com.marcosanz.core_ui.theme.defaults.AppTextStyle

@Composable
fun GalleryTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = AppLightScheme,
        shapes = AppShapes,
        typography = AppTypography,
        content = content
    )
}

private val AppLightScheme = lightColorScheme()
private val AppShapes = Shapes(
    extraSmall = AppCornerRadius.smallShape,
    small = AppCornerRadius.smallShape,
    medium = AppCornerRadius.mediumShape,
    large = AppCornerRadius.largeShape,
    extraLarge = AppCornerRadius.extraLargeShape
)

private val AppTypography = Typography(
    displayLarge = AppTextStyle.Body.body.copyForTheme(),
    displayMedium = AppTextStyle.Body.body.copyForTheme(),
    displaySmall = AppTextStyle.Body.body.copyForTheme(),
    headlineLarge = AppTextStyle.Body.body.copyForTheme(),
    headlineMedium = AppTextStyle.Body.body.copyForTheme(),
    headlineSmall = AppTextStyle.Body.body.copyForTheme(),
    titleLarge = AppTextStyle.Body.body.copyForTheme(),
    titleMedium = AppTextStyle.Body.body.copyForTheme(),
    titleSmall = AppTextStyle.Body.body.copyForTheme(),
    bodyLarge = AppTextStyle.Body.body.copyForTheme(),
    bodyMedium = AppTextStyle.Body.body.copyForTheme(),
    bodySmall = AppTextStyle.Body.body.copyForTheme(),
    labelLarge = AppTextStyle.Button.button.copyForTheme(),
    labelMedium = AppTextStyle.Button.button.copyForTheme(),
    labelSmall = AppTextStyle.Button.button.copyForTheme()
)