package com.marcosanz.gallery_core_ui.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.graphics.scale
import coil3.BitmapImage
import coil3.SingletonImageLoader
import coil3.imageLoader
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.request.error
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Asynchronously fetches an image from the provided [data] and converts it into a [Bitmap].
 *
 * This function uses the Coil image loading library to execute the request. Hardware bitmaps
 * are disabled to ensure the resulting bitmap can be safely manipulated or saved to storage.
 *
 * @param context The context used to initialize the image request and access the image loader
 * @param data The data source for the image (e.g., URL, URI, File, or resource ID)
 * @param allowHardware Whether hardware bitmaps should be allowed to be used. Hardware bitmaps doesn't work with PLManager
 *
 * @return A [Bitmap] representation of the requested image or null if something failed
 */
suspend fun Context.requestBitmap(
    data: Any?,
    allowHardware: Boolean
): Bitmap? = withContext(Dispatchers.IO) {

    if (data == null)
        return@withContext null

    val loader = SingletonImageLoader.get(this@requestBitmap)
    val request = ImageRequest.Builder(this@requestBitmap)
        .data(data = data)
        .allowHardware(enable = allowHardware)
        .build()

    when (val result = loader.execute(request)) {
        is SuccessResult -> {
            val bitmap =
                (result.image as? BitmapImage)?.bitmap

            bitmap
        }

        is ErrorResult -> null
    }
}