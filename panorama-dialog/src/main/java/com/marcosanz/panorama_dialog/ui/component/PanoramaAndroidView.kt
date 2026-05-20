package com.marcosanz.panorama_dialog.ui.component

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.marcosanz.gallery_core_ui.extensions.requestBitmap
import com.marcosanz.panorama_dialog.util.extension.rememberGestureDetector
import com.marcosanz.panorama_dialog.util.extension.rememberPlManager
import com.panoramagl.PLImage
import com.panoramagl.PLManager
import com.panoramagl.PLSphericalPanorama

@SuppressLint("ClickableViewAccessibility")
@Suppress("MultipleContentEmitters")
@Composable
internal fun PanoramaAndroidView(
    model: Any,
    isSensorialRotationEnabled: Boolean,
    modifier: Modifier = Modifier,
    plManager: PLManager? = rememberPlManager(),
    onSingleTap: () -> Boolean,
) {
    val context = LocalContext.current

    var bitmap: Bitmap? by remember { mutableStateOf(null) }
    var lastLoadedModel by remember { mutableStateOf<Any?>(null) }

    LaunchedEffect(model) {
        // IMPORTANT: Allow Hardware must be FALSE to work correctly with PanoramaGL
        bitmap = context.requestBitmap(data = model, allowHardware = false)
    }


    val gestureDetector = rememberGestureDetector(
        context = context,
        onSingleTap = {
            onSingleTap()
        },
        onDoubleTap = {
            val camera = plManager?.camera ?: return@rememberGestureDetector false

            // Zoom Out
            if (camera.zoomFactor > 0.0)
                camera.setZoomFactor(0.0f,true)
            // Zoom In
            else
                camera.setZoomFactor(0.75f,true)

            true
        }
    )

    LaunchedEffect(isSensorialRotationEnabled, plManager) {
        plManager ?: return@LaunchedEffect
        if (isSensorialRotationEnabled) {
            plManager.startSensorialRotation()
        } else {
            plManager.stopSensorialRotation()
        }
    }

    AndroidView(
        factory = { ctx ->
            FrameLayout(ctx).also { frameLayout ->
                frameLayout.id = View.generateViewId()

                // 2. Initial Setup
                plManager?.setContentView(frameLayout)
                plManager?.onCreate()
                plManager?.onResume()


                // 3. Touch capture
                frameLayout.setOnTouchListener { _, event ->
                    gestureDetector.onTouchEvent(event)
                    plManager?.onTouchEvent(event)
                    true
                }
            }
        },
        modifier = modifier.fillMaxSize(),
        update = {
            val currentBitmap = bitmap
            // 4. Update bitmap if it changes
            if (currentBitmap != null && lastLoadedModel != model) {
                val panorama = PLSphericalPanorama().apply {
                    setImage(PLImage(currentBitmap))
                }
                plManager?.panorama = panorama
                lastLoadedModel = model
            }
        }
    )
}