package com.marcosanz.panorama_dialog.util.extension

import androidx.compose.runtime.Composable
import com.marcosanz.panorama_dialog.PanoramaDialogOptions

@Composable
fun rememberPanoramaDialogOptions(
    error: Int? = null,
    placeholder: Int? = null,
    isUiVisible: Boolean = true,
    isDescriptionVisible: Boolean = true,
    isRotationButtonVisible: Boolean = true,
    isSensorialRotationButtonVisible: Boolean = true,
    isSensorialRotationButtonEnabled: Boolean = true
): PanoramaDialogOptions {
    return PanoramaDialogOptions(
        error = error,
        placeholder = placeholder,
        isUiVisible = isUiVisible,
        isDescriptionVisible = isDescriptionVisible,
        isRotationButtonVisible = isRotationButtonVisible,
        isSensorialRotationButtonVisible = isSensorialRotationButtonVisible,
        isSensorialRotationButtonEnabled = isSensorialRotationButtonEnabled
    )
}