package com.marcosanz.app.feature

import com.marcosanz.app.core.platform.BaseViewModel
import com.marcosanz.gallery_common.model.GalleryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : BaseViewModel<MainAction, MainEvent, MainState>(
    initialState = MainState()
) {

    override suspend fun handleAction(action: MainAction) {
        when (action) {
            // Gallery
            is MainAction.Gallery.OnItemSelect -> galleryItemSelect(item = action.item)
            MainAction.Gallery.UnselectItem -> unselectItems()
            // Panorama
            is MainAction.Panorama.OnItemSelect -> panoramaItemSelect(item = action.item)
            MainAction.Panorama.UnselectItem -> unselectItems()
        }
    }

    private fun galleryItemSelect(item: GalleryItem) {
        _uiState.update { state ->
            state.copy(
                selectedGalleryIndex = state.galleryItems.indexOf(item),
            )
        }
    }

    private fun panoramaItemSelect(item: GalleryItem) {
        _uiState.update { state ->
            state.copy(
                selectedPanoramaIndex = state.panoramaItems.indexOf(item)
            )
        }
    }

    private fun unselectItems() {
        _uiState.update { state ->
            state.copy(
                selectedGalleryIndex = null,
                selectedPanoramaIndex = null
            )
        }
    }

}