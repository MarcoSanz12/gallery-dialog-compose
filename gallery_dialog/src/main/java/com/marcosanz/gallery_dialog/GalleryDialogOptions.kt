package com.marcosanz.gallery_dialog

import androidx.annotation.DrawableRes

/**
 * Options for customizing the [GalleryDialog]
 *
 * @param error Drawable to show when the image fails to load
 * @param placeholder Drawable to show while the image is loading
 * @param isUiVisible Whether the UI of the dialog is initially visible or not
 * @param isDescriptionVisible Whether the description of the image is visible or not
 * @param isRotationEnabled Whether the rotate button is visible
 * @param isDownloadEnabled Whether the download button is visible
 * @param isShareEnabled Whether the share button is visible
 */
data class GalleryDialogOptions(
    @param:DrawableRes val error : Int? = null,
    @param:DrawableRes val placeholder : Int? = null,
    val isUiVisible : Boolean = true,
    val isDescriptionVisible : Boolean = true,
    val isRotationEnabled : Boolean = true,
    val isDownloadEnabled : Boolean = true,
    val isShareEnabled : Boolean = true
)
