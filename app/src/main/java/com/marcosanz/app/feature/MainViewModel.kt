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
            is MainAction.Gallery.OnItemSelect -> galleryItemSelect(item = action.item)
            MainAction.Gallery.UnselectItem -> galleryUnselect()
        }
    }

    private fun galleryItemSelect(item: GalleryItem) {
        _uiState.update { state ->
            state.copy(
                selectedItem = item
            )
        }
    }

    private fun galleryUnselect() {
        _uiState.update { state ->
            state.copy(
                selectedItem = null
            )
        }
    }
}