package com.marcosanz.gallery_core_ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PreviewScaffold(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    GalleryTheme {
        Surface {
            Column(
                modifier = modifier
                    .background(backgroundColor ?: MaterialTheme.colorScheme.background)
                    .windowInsetsPadding(WindowInsets.systemBars),
                content = content
            )
        }
    }
}