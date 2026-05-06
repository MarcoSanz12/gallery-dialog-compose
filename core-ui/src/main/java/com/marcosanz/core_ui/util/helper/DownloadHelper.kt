package com.marcosanz.core_ui.util.helper

import android.content.ContentValues
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.provider.MediaStore
import coil3.Bitmap
import coil3.SingletonImageLoader
import coil3.asDrawable
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import com.marcosanz.core_ui.R
import com.marcosanz.core_ui.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DownloadHelper {

    suspend fun downloadImage(
        context: Context,
        data: Any?,
        currentDateTime: LocalDateTime = LocalDateTime.now()
    ) {
        // ❌ No data
        if (data == null)
            return

        // In progress
        context.showImageSaveInProgressToast()

        val bitmap = requestBitmap(
            context = context,
            data = data
        )

        // ❌ Error obtaining Bitmap
        if (bitmap == null) {
            context.showImageSaveFailedToast()
            return
        }

        // ✅ Save in Gallery
        saveBitmapToGallery(
            context = context,
            bitmap = bitmap,
            fileName = getFilename(currentDateTime = currentDateTime),
            onImageSaved = {
                context.showImageSavedToast()
            },
            onImageFailed = {
                context.showImageSaveFailedToast()
            }
        )
    }

    private fun Context.showImageSaveInProgressToast() {
        showToast(message = getString(R.string.gallery_dialog_download_progress))
    }

    private fun Context.showImageSavedToast() {
        showToast(message = getString(R.string.gallery_dialog_download_success))
    }

    private fun Context.showImageSaveFailedToast() {
        showToast(message = getString(R.string.gallery_dialog_download_failure))
    }

    private fun getFilename(currentDateTime: LocalDateTime): String {
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
    }


    /**
     * Asynchronously fetches an image from the provided [data] and converts it into a [Bitmap].
     *
     * This function uses the Coil image loading library to execute the request. Hardware bitmaps
     * are disabled to ensure the resulting bitmap can be safely manipulated or saved to storage.
     *
     * @param context The context used to initialize the image request and access the image loader.
     * @param data The data source for the image (e.g., URL, URI, File, or resource ID).
     *
     * @return A [Bitmap] representation of the requested image or null if something failed
     */
    suspend fun requestBitmap(
        context: Context,
        data: Any?,
    ): Bitmap? = withContext(Dispatchers.IO) {

        if (data == null)
            return@withContext null

        val loader = SingletonImageLoader.get(context)
        val request = ImageRequest.Builder(context)
            .data(data = data)
            .build()

        when (val result = loader.execute(request)) {
            is SuccessResult -> {
                val bitmap =
                    (result.image.asDrawable(context.resources) as? BitmapDrawable)?.bitmap
                bitmap
            }

            is ErrorResult -> null
        }
    }

    private fun saveBitmapToGallery(
        context: Context,
        bitmap: Bitmap,
        fileName: String,
        onImageSaved: () -> Unit,
        onImageFailed: () -> Unit
    ) {
        try {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "$fileName.jpeg")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            if (uri == null) {
                onImageFailed()
                return
            }

            resolver.openOutputStream(uri)?.use { stream ->
                bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, stream)
            }

            onImageSaved()

        } catch (ex: Exception) {
            ex.printStackTrace()
            onImageFailed()
        }
    }
}