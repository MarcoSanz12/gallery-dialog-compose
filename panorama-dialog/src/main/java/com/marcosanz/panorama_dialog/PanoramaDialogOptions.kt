package com.marcosanz.panorama_dialog

import androidx.annotation.DrawableRes

/**
 * Options for customizing the [PanoramaDialog]
 *
 * @param error Drawable to show when the image fails to load
 * @param placeholder Drawable to show while the image is loading
 * @param isUiVisible Whether the UI of the dialog is initially visible or not
 * @param isDescriptionVisible Whether the description of the image is visible or not
 * @param isRotationButtonVisible Whether the rotate button is visible
 * @param isSensorialRotationButtonVisible Whether the sensory rotation button is visible
 * @param isSensorialRotationButtonEnabled Whether the sensory rotation button is enabled by default
 */
data class PanoramaDialogOptions(
    @param:DrawableRes val error : Int? = null,
    @param:DrawableRes val placeholder : Int? = null,
    val isUiVisible : Boolean = true,
    val isDescriptionVisible : Boolean = true,
    val isRotationButtonVisible : Boolean = true,
    val isSensorialRotationButtonVisible : Boolean = true,
    val isSensorialRotationButtonEnabled : Boolean = true,
)
