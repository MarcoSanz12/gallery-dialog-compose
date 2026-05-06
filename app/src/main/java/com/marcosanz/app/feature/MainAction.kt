package com.marcosanz.app.feature


import com.marcosanz.gallery_dialog.GalleryItem

sealed interface MainAction {

    sealed interface Gallery : MainAction{
        data class OnItemSelect(val item : GalleryItem) : MainAction
        object UnselectItem : MainAction
    }

}