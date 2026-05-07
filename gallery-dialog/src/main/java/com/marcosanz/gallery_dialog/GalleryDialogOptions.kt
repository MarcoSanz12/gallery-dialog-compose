package com.marcosanz.gallery_dialog

import androidx.annotation.DrawableRes

/**
 * Options for customizing the [GalleryDialog]
 *
 * @param error Drawable to show when the image fails to load
 * @param placeholder Drawable to show while the image is loading
 * @param isUiVisible Whether the UI of the dialog is initially visible or not
 * @param isDescriptionButtonVisible Whether the description of the image is visible or not
 * @param isRotationButtonVisible Whether the rotate button is visible
 * @param isDownloadButtonVisible Whether the download button is visible
 * @param isShareButtonVisible Whether the share button is visible
 */
data class GalleryDialogOptions(
    @param:DrawableRes val error : Int? = null,
    @param:DrawableRes val placeholder : Int? = null,
    val isUiVisible : Boolean = true,
    val isDescriptionButtonVisible : Boolean = true,
    val isRotationButtonVisible : Boolean = true,
    val isDownloadButtonVisible : Boolean = true,
    val isShareButtonVisible : Boolean = true
)
