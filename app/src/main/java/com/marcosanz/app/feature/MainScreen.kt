package com.marcosanz.app.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.marcosanz.app.R
import com.marcosanz.app.core.ui.component.common.SpacerExtraLarge
import com.marcosanz.app.core.ui.component.image.ExampleImageRow
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_dialog.GalleryDialog
import com.marcosanz.gallery_dialog.util.extension.rememberGalleryDialogOptions
import com.marcosanz.panorama_dialog.PanoramaDialog

@Preview
@Composable
private fun MainScreenPreview() {
    PreviewScaffold() {
        MainScreen(
            uiState = MainState(),
            onAction = {}
        )
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val uiState by viewModel.collectUiState()

    MainScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
private fun MainScreen(uiState: MainState, onAction: (MainAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Gallery images
        ExampleImageRow(
            items = uiState.galleryItems,
            title = "GalleryDialog",
            onGalleryItemClick = {
                onAction(MainAction.Gallery.OnItemSelect(it))
            }
        )
        SpacerExtraLarge()

        // Panorama images
        ExampleImageRow(
            items = uiState.panoramaItems,
            title = "PanoramaDialog",
            onGalleryItemClick = {
                onAction(MainAction.Panorama.OnItemSelect(it))
            }
        )
    }

    // Gallery Dialog
    if (uiState.isGalleryDialogVisible)
        GalleryDialog(
            items = uiState.galleryItems,
            initialItem = uiState.galleryItems.indexOf(uiState.selectedGalleryItem),
            options = rememberGalleryDialogOptions(
                error = R.drawable.drawable_error,
                placeholder = R.drawable.drawable_placeholder
            ),
            onDismissRequest = {
                onAction(MainAction.Gallery.UnselectItem)
            }
        )

    // Panorama Dialog
    if (uiState.isPanoramaDialogVisible && uiState.selectedPanoramaItem != null)
        PanoramaDialog(
            item = uiState.selectedPanoramaItem!!,
            onDismissRequest = {
                onAction(MainAction.Panorama.UnselectItem)
            }
        )
}