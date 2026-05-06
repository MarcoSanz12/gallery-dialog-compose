package com.marcosanz.gallery_dialog

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.marcosanz.core_ui.ui.GalleryBottomBar
import com.marcosanz.core_ui.ui.GalleryTopBar
import com.marcosanz.core_ui.util.controller.EdgeToEdgeController
import com.marcosanz.core_ui.util.controller.OrientationController
import com.marcosanz.core_ui.util.helper.DownloadHelper
import com.marcosanz.core_ui.util.helper.ShareHelper
import com.marcosanz.gallery_dialog.extension.rememberGalleryDialogOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Preview
@Composable
private fun GalleryDialogPreview() {
    GalleryDialog(
        items = listOf(
            GalleryItem(
                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdnSj_ePgbGDuzLJwvMXBneYkiVU9aPqY7pZEDvXty5mUFtDoMlmyVb3piUk6uqeC1rWnDFETXX7QRtBDWItAo74ipNslZF9j9uYKyNW0&s=10"
            ),
            GalleryItem(
                model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdnSj_ePgbGDuzLJwvMXBneYkiVU9aPqY7pZEDvXty5mUFtDoMlmyVb3piUk6uqeC1rWnDFETXX7QRtBDWItAo74ipNslZF9j9uYKyNW0&s=10"
            )
        ),
        onDismissRequest = {}
    )
}

/**
 * Opens a Dialog with images that can be zoomed and swiped.
 *
 * The Dialog works using [GalleryItem]
 *
 * @param items List of items to show in the dialog
 * @param initialItem Index of the item to show first
 * @param options Options for customizing the dialog
 * @param onDismissRequest Callback to be invoked when the dialog is dismissed
 *
 */
@Composable
fun GalleryDialog(
    items: List<GalleryItem>,
    initialItem: Int = 0,
    options: GalleryDialogOptions = rememberGalleryDialogOptions(),
    onDismissRequest: () -> Unit,
) {
    var isUiVisible by remember(options.isUiVisible) {
        mutableStateOf(options.isUiVisible)
    }

    val scope: CoroutineScope = rememberCoroutineScope()
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
                val pagerState = rememberPagerState(
                    initialPage = initialItem.coerceIn(0, items.size),
                    pageCount = { items.size }
                )

                val currentItem: GalleryItem? = remember(pagerState.currentPage, items) {
                    items.getOrNull(pagerState.currentPage)
                }

                val descriptions = remember(items) {
                    items.map { it.description }
                }

                EdgeToEdgeController.SystemBarsVisibility(visible = isUiVisible)

                ResetOrientationEffect(context = context)

                // Images Horizontal Pager
                ImagePager(
                    modifier = Modifier,
                    pagerState = pagerState,
                    items = items,
                    error = options.error,
                    placeholder = options.placeholder,
                    onClick = {
                        isUiVisible = !isUiVisible
                    }
                )

                // TopBar
                GalleryTopBar(
                    isVisible = isUiVisible,
                    modifier = Modifier.fillMaxWidth(),
                    isDownloadButtonVisible = options.isDownloadEnabled,
                    isShareButtonVisible = options.isShareEnabled,
                    isRotateButtonVisible = options.isRotationEnabled,
                    onBack = {
                        onDismissRequest()
                    },
                    onRotate = {
                        OrientationController.toggleOrientation(context = context)
                    },
                    onDownload = onDownload(
                        currentItem = currentItem,
                        scope = scope,
                        context = context
                    ),
                    onShare = onShare(
                        scope = scope,
                        context = context,
                        currentItem = currentItem
                    )
                )

                val isBottomBarVisible = rememberBottomBarVisibility(
                    isUiVisible = isUiVisible,
                    options = options
                )
                // BottomBar
                GalleryBottomBar(
                    isVisible = isBottomBarVisible,
                    modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    pagerState = pagerState,
                    texts = descriptions,
                )
            }
        }
    )
}

@Composable
private fun rememberBottomBarVisibility(
    isUiVisible: Boolean,
    options: GalleryDialogOptions
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

@Composable
private fun onShare(
    scope: CoroutineScope,
    context: Context,
    currentItem: GalleryItem?
): () -> Unit = {
    scope.launch {
        ShareHelper.shareImage(
            context = context,
            data = currentItem?.model
        )
    }
}

@Composable
private fun onDownload(
    currentItem: GalleryItem?,
    scope: CoroutineScope,
    context: Context
): () -> Unit = {
    val model = currentItem?.model
    scope.launch {
        DownloadHelper.downloadImage(
            context = context,
            data = model
        )
    }
}

@Composable
private fun ImagePager(
    pagerState: PagerState,
    items: List<GalleryItem>,
    modifier: Modifier = Modifier,
    @DrawableRes error: Int? = null,
    @DrawableRes placeholder: Int? = null,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        pageSpacing = 0.dp
    ) { pageIndex ->
        val item = items[pageIndex]
        val zoomState = rememberZoomState(
            contentSize = Size(1f, 1f)
        )

        // Reset zoom of previous image after moving to another one
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (pagerState.currentPage != pageIndex && !pagerState.isScrollInProgress)
                zoomState.reset()
        }

        val request = remember(item.model, error, placeholder) {
            ImageRequest.Builder(context)
                .data(item.model)
                .let {
                    var builder = it
                    // Placeholder
                    if (placeholder != null)
                        builder = builder.placeholder(drawableResId = placeholder)
                    // Error
                    if (error != null)
                        builder =
                            builder
                                .error(drawableResId = error)
                                .fallback(drawableResId = error)
                    builder
                }
                .build()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(request),
                contentDescription = item.description,
                modifier = Modifier
                    .fillMaxSize()
                    .zoomable(
                        zoomState = zoomState,
                        onTap = { onClick() }
                    )
            )
        }
    }
}