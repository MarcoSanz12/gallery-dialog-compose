package com.marcosanz.gallery_dialog.util.helper

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import coil3.Bitmap
import com.marcosanz.gallery_core_ui.extensions.requestBitmap
import com.marcosanz.gallery_core_ui.extensions.showToast
import com.marcosanz.gallery_dialog.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal object DownloadHelper {

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

        val bitmap = context.requestBitmap(
            data = data,
            allowHardware = true
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