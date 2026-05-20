package com.marcosanz.app.feature

import com.marcosanz.app.util.provider.ExampleItemsProvider
import com.marcosanz.gallery_common.model.GalleryItem

data class MainState(
    val galleryItems: List<GalleryItem> = ExampleItemsProvider.galleryItems,
    val panoramaItems : List<GalleryItem> = ExampleItemsProvider.panoramaItems,
    val selectedGalleryIndex: Int? = null,
    val selectedPanoramaIndex: Int? = null
) {
    val isGalleryDialogVisible get() = selectedGalleryIndex != null
    val isPanoramaDialogVisible get() = selectedPanoramaIndex != null

    val selectedGalleryItem: GalleryItem?
        get() = selectedGalleryIndex?.let { galleryItems.getOrNull(it) }

    val selectedPanoramaItem: GalleryItem?
        get() = selectedPanoramaIndex?.let { panoramaItems.getOrNull(it) }
}