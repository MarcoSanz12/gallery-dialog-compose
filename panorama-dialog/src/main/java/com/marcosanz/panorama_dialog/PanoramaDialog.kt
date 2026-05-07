package com.marcosanz.panorama_dialog

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.marcosanz.gallery_common.model.GalleryItem
import com.marcosanz.gallery_core_ui.ui.BasicBottomBar
import com.marcosanz.gallery_core_ui.util.controller.EdgeToEdgeController
import com.marcosanz.gallery_core_ui.util.controller.OrientationController
import com.marcosanz.panorama_dialog.ui.component.PanoramaTopBar
import com.marcosanz.panorama_dialog.util.extension.rememberPanoramaDialogOptions

@Preview
@Composable
private fun GalleryDialogPreview() {
    GalleryDialog(
        item =
            GalleryItem(
                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdnSj_ePgbGDuzLJwvMXBneYkiVU9aPqY7pZEDvXty5mUFtDoMlmyVb3piUk6uqeC1rWnDFETXX7QRtBDWItAo74ipNslZF9j9uYKyNW0&s=10"
            ),
        onDismissRequest = {}
    )
}

/**
 * Opens a Dialog with a panoramic image that can be zoomed and rotated.
 *
 * The Dialog works using [GalleryItem]
 *
 * @param item Item shown in the dialog
 * @param options Options for customizing the dialog
 * @param onDismissRequest Callback to be invoked when the dialog is dismissed
 *
 */
@Composable
fun GalleryDialog(
    item: GalleryItem,
    options: PanoramaDialogOptions = rememberPanoramaDialogOptions(),
    onDismissRequest: () -> Unit,
) {
    var isUiVisible by remember(options.isUiVisible) {
        mutableStateOf(options.isUiVisible)
    }

    val context: Context = LocalContext.current

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        ),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
            ) {
                EdgeToEdgeController.SystemBarsVisibility(visible = isUiVisible)

                ResetOrientationEffect(context = context)

                // TopBar
                PanoramaTopBar(
                    isVisible = isUiVisible,
                    modifier = Modifier.fillMaxWidth(),
                    isRotateButtonVisible = options.isRotationButtonVisible,
                    isSensorialRotationButtonEnabled = options.isSensorialRotationButtonEnabled,
                    isSensorialRotationButtonVisible = options.isSensorialRotationButtonVisible,
                    onBack = {
                        onDismissRequest()
                    },
                    onRotate = {
                        OrientationController.toggleOrientation(context = context)
                    },
                    onSensorialRotation = {

                    }
                )

                val isBottomBarVisible = rememberBottomBarVisibility(
                    isUiVisible = isUiVisible,
                    options = options
                )
                // BottomBar
                BasicBottomBar(
                    isVisible = isBottomBarVisible,
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    text = item.description
                )
            }
        }
    )
}

@Composable
private fun rememberBottomBarVisibility(
    isUiVisible: Boolean,
    options: PanoramaDialogOptions
): Boolean = remember(isUiVisible, options.isDescriptionVisible) {
    isUiVisible && options.isDescriptionVisible
}

@Composable
private fun ResetOrientationEffect(context: Context) {
    val initialOrientation = rememberSaveable {
        OrientationController.getRequestedOrientation(context = context)
    }

    DisposableEffect(Unit) {
        onDispose {
            val activity = OrientationController.run { context.findActivity() }

            if (activity != null && !activity.isChangingConfigurations)
                OrientationController.setOrientation(
                    context = context,
                    orientation = initialOrientation
                )
        }
    }
}