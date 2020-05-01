package com.basefy.core_utility

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.app.ActivityCompat
import android.provider.Settings;


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
    }


}
