package com.marcosanz.core_ui.theme.defaults

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object AppCornerRadius {
    val small = 4.dp
    val medium = 8.dp
    val large = 16.dp
    val extraLarge = 24.dp
    

    val smallShape = RoundedCornerShape(small)
    val mediumShape = RoundedCornerShape(medium)
    val largeShape = RoundedCornerShape(large)
    val extraLargeShape = RoundedCornerShape(extraLarge)
}