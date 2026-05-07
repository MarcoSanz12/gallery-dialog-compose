package com.marcosanz.gallery_dialog.util.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.marcosanz.gallery_dialog.GalleryDialog
import com.marcosanz.gallery_dialog.GalleryDialogOptions

/**
 * Options for customizing a [GalleryDialog]
 *
 * @param error Drawable to show when the image fails to load
 * @param placeholder Drawable to show while the image is loading
 * @param isUiVisible Whether the UI of the dialog is initially visible or not
 * @param isDescriptionVisible Whether the description of the image is visible or not
 * @param isRotationEnabled Whether the rotate button is visible
 * @param isDownloadEnabled Whether the download button is visible
 * @param isShareEnabled Whether the share button is visible
 */
@Composable
fun rememberGalleryDialogOptions(
    error: Int? = null,
    placeholder: Int? = null,
    isUiVisible: Boolean = true,
    isDescriptionVisible: Boolean = true,
    isRotationEnabled: Boolean = true,
    isDownloadEnabled: Boolean = true,
    isShareEnabled: Boolean = true
) = remember(
    error,
    placeholder,
    isUiVisible,
    isDescriptionVisible,
    isRotationEnabled,
    isDownloadEnabled,
    isShareEnabled
) {
    GalleryDialogOptions(
        error = error,
        placeholder = placeholder,
        isUiVisible = isUiVisible,
        isDescriptionButtonVisible = isDescriptionVisible,
        isRotationButtonVisible = isRotationEnabled,
        isDownloadButtonVisible = isDownloadEnabled,
        isShareButtonVisible = isShareEnabled

    )
}