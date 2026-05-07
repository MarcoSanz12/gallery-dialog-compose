package com.marcosanz.gallery_dialog.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.ui.BasicTopBar
import com.marcosanz.gallery_core_ui.ui.GalleryRotateButton
import com.marcosanz.gallery_dialog.R


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
internal fun GalleryTopBar(
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
    BasicTopBar(
        modifier = modifier,
        isVisible = isVisible,
        onBack = onBack
    ){

    }
    BasicTopBar(
        modifier = modifier,
        isVisible = isVisible,
        onBack = onBack
    ) {
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