package com.marcosanz.app.feature

import com.marcosanz.app.util.provider.ExampleItemsProvider
import com.marcosanz.gallery_dialog.GalleryItem

data class MainState(
    val galleryItems: List<GalleryItem> = ExampleItemsProvider.drawableItems,
    val selectedItem: GalleryItem? = null
) {
    val isGalleryDialogVisible get() = selectedItem != null
}

