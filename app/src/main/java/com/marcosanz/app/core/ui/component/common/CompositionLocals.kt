package com.marcosanz.app.core.ui.component.common

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.marcosanz.app.core.ui.component.button.ButtonBasicColors
import com.marcosanz.app.core.ui.component.button.ButtonBasicDefaults

val LocalButtonBasicColors: ProvidableCompositionLocal<ButtonBasicColors> =
    compositionLocalOf {
        ButtonBasicDefaults.Color.Primary.default
    }