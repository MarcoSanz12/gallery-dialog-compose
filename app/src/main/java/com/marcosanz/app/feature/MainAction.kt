package com.marcosanz.app.feature


import com.marcosanz.gallery_common.model.GalleryItem

sealed interface MainAction {

    sealed interface Gallery : MainAction{
        data class OnItemSelect(val item : GalleryItem) : MainAction
        object UnselectItem : MainAction
    }

    sealed interface Panorama : MainAction{
        data class OnItemSelect(val item : GalleryItem) : MainAction
        object UnselectItem : MainAction
    }
}