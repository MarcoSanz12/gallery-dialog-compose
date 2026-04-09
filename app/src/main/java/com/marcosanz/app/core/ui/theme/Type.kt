package com.marcosanz.app.core.ui.theme

import androidx.compose.material3.Typography
import com.marcosanz.app.core.extensions.ui.text.copyForTheme
import com.marcosanz.app.core.ui.theme.defaults.AppTextStyle

/*val DMSansFontFamily = FontFamily(
    // Standard
    Font(R.font.dmsans_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.dmsans_regular_italic, FontWeight.Normal, FontStyle.Italic),

    // Medium
    Font(R.font.dmsans_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.dmsans_medium_italic, FontWeight.Medium, FontStyle.Italic),

    // Bold
    Font(R.font.dmsans_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.dmsans_bold_italic, FontWeight.Bold, FontStyle.Italic),
)

val PanoFontFamily = FontFamily(
    Font(R.font.pano_bold, FontWeight.Bold, FontStyle.Normal)
)*/

val AppTypography = Typography(
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
