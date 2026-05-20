package com.marcosanz.panorama_dialog.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.marcosanz.gallery_core_ui.theme.PreviewScaffold
import com.marcosanz.gallery_core_ui.ui.BasicTopBar
import com.marcosanz.gallery_core_ui.ui.GalleryRotateButton

@Preview
@Composable
private fun PanoramaTopBarPreview() {
    PreviewScaffold() {
        PanoramaTopBar()
    }
}

@Composable
internal fun PanoramaTopBar(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    isRotateButtonVisible: Boolean = true,
    isSensorialRotationButtonChecked: Boolean = true,
    isSensorialRotationButtonVisible: Boolean = true,
    onBack: () -> Unit = {},
    onRotate: () -> Unit = {},
    onSensorialRotation: () -> Unit = {}
) {
    BasicTopBar(
        modifier = modifier,
        isVisible = isVisible,
        onBack = onBack
    ) {

        // 🗣️ Sensorial Rotation
        SensorialRotationButton(
            isVisible = isSensorialRotationButtonVisible,
            checked = isSensorialRotationButtonChecked,
            onClick = onSensorialRotation
        )
        // 🔄 Rotate
        GalleryRotateButton(
            isVisible = isRotateButtonVisible,
            onClick = onRotate
        )
    }
}