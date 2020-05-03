package com.basefy.core_utility

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


class CoreCommonUtils {
    companion object {
        /**
         * Loading göstermek amacıyla kullanılır
         * @param context: Bu fonksiyonun çağırıldığı contexttir
         */
        fun showLoadingDialog(context: Context): ProgressDialog {
            var progressDialog: ProgressDialog = ProgressDialog(context)
            if (!(context as Activity).isFinishing) {
                progressDialog.show()
            }
            if (progressDialog.window != null) {
                progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            /*progressDialog.setCancelable(false)*/
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }

        /**
         * Cihaza spesifik bir id oluşturmak amacıyla kullanılır
         * @param context: Bu fonksiyonun çağırıldığı contexttir
         */
        fun getUdid(context: Context): String? {
            return Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }


         fun saveImage(activity: Activity,bitmap: Bitmap, folderName: String,saveImageCallback :() -> Unit) {

            if (android.os.Build.VERSION.SDK_INT >= 29) {
                val values = contentValues()
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + folderName)
                values.put(MediaStore.Images.Media.IS_PENDING, true)
                val uri: Uri? =
                    activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                if (uri != null) {
                    saveImageToStream(bitmap, activity.contentResolver.openOutputStream(uri))
                    values.put(MediaStore.Images.Media.IS_PENDING, false)
                    activity.contentResolver.update(uri, values, null, null)
                }
            } else {
                var filename = UUID.randomUUID()
                var file =
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath().toString() + File.separator + folderName)
                if (!file.exists()) {
                    file.mkdir()
                }
                file =
                    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath().toString() + File.separator + folderName + File.separator + filename + ".jpg")

                saveImageToStream(bitmap, FileOutputStream(file))
                if (file.absolutePath != null) {
                    val values = contentValues()
                    values.put(MediaStore.Images.Media.DATA, file.absolutePath)
                    activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                }
            }
             saveImageCallback()
        }

        fun getImageUri(
            inContext: Context,
            inImage: Bitmap
        ): Uri? {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path: String =
                MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
            return Uri.parse(path)
        }

        private fun contentValues(): ContentValues {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            return values
        }

        private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?) {
            if (outputStream != null) {
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}
