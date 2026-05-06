package com.marcosanz.core_ui.util.helper

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.marcosanz.core_ui.R
import com.marcosanz.core_ui.extensions.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object ShareHelper {

    suspend fun shareImage(
        context: Context,
        data: Any?
    ) {
        try {
            val bitmap = DownloadHelper.requestBitmap(context = context, data = data)

            // ❌ Error obtaining Bitmap
            if (bitmap == null){
                context.showErrorSharingToast()
                return
            }
            val file = bitmapToCacheFile(context = context, bitmap = bitmap)
            val uri = fileToUri(context = context, file = file)

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.gallery_dialog_share_image_title)
                )
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            context.showErrorSharingToast()
        }
    }

    private fun fileToUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.gallerydialog.provider",
            file
        )
    }

    /**
     * Saves a [Bitmap] to a temporary file in the application's cache directory.
     *
     * This function creates a PNG file with a unique name based on the current system time.
     * The resulting file is intended for temporary use, such as sharing via an Intent.
     *
     * @param context The context used to access the cache directory.
     * @param bitmap The bitmap to be saved to the cache.
     * @return A [File] object pointing to the newly created image in the cache directory.
     */
    private fun bitmapToCacheFile(context: Context, bitmap: Bitmap): File {
        val file = File(context.cacheDir, "share_${System.currentTimeMillis()}.png")

        file.outputStream().use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        return file
    }

    private fun Context.showErrorSharingToast(){
        showToast(getString(R.string.gallery_dialog_share_error))
    }
}
