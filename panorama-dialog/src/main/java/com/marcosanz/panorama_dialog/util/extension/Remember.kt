package com.marcosanz.panorama_dialog.util.extension

import android.app.Activity
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.marcosanz.panorama_dialog.PanoramaDialogOptions
import com.panoramagl.PLManager

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


@Composable
internal fun rememberGestureDetector(
    context: Context = LocalContext.current,
    onSingleTap: () -> Boolean,
    onDoubleTap: () -> Boolean
): GestureDetector {
    return remember(context) {
        GestureDetector(
            context,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    return onSingleTap()
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    return onDoubleTap()
                }
            }
        )
    }
}

@Composable
internal fun rememberPlManager(activity: Activity? = LocalActivity.current): PLManager? {
    val manager = remember(activity) {
        activity ?: return@remember null

        PLManager(activity).apply {
            isAcceleratedTouchScrollingEnabled = true
            isZoomEnabled = true
            activateOrientation()
        }
    }

    DisposableLifecycleEffect(manager = manager, lifecycleOwner = LocalLifecycleOwner.current)
    return manager
}


@Composable
private fun DisposableLifecycleEffect(
    manager: PLManager?,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    DisposableEffect(lifecycleOwner, manager) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> manager?.onResume()
                Lifecycle.Event.ON_PAUSE -> manager?.onPause()
                Lifecycle.Event.ON_DESTROY -> manager?.onDestroy()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            manager?.onDestroy()
        }
    }
}
