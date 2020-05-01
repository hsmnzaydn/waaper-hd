package com.basefy.base_mvp

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.basefy.core_utility.CoreCommonUtils
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity(), MvpView, BaseFragment.Callback {

    var progressDialog: ProgressDialog? = null
    private var alertDialogBuilder: AlertDialog.Builder? = null



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

        super.onCreate(savedInstanceState, persistentState)

    }

    override fun showLoading() {
        if (progressDialog != null) {
            if (!progressDialog!!.isShowing) {
                progressDialog!!.show()
            }
        } else {
            progressDialog = CoreCommonUtils.showLoadingDialog(this@BaseActivity)
        }
    }

    override fun hideLoading() {
        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()

            }
        }
    }



    override fun showInformation(text: String) {
        Toast.makeText(this@BaseActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun showError(text: String) {
        Toast.makeText(this@BaseActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE

                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    override fun showDialogWithOutChoose(
        title: String,
        description: String,
        negativeButtonText: String?,
        onClickNegativeButton: () -> Unit
    ) {
        alertDialogBuilder = AlertDialog.Builder(this)

        alertDialogBuilder!!.setTitle(title).setMessage(description).setPositiveButton("",
            DialogInterface.OnClickListener { dialog, which -> onClickNegativeButton() })
            .setNegativeButton(
                negativeButtonText,
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        val alertDialog = alertDialogBuilder!!.create()

        alertDialog.setOnShowListener(DialogInterface.OnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.red))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.red))
        })

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()

    }



    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }



    override fun addScrollableToFullScreenView(statusBarColor: Int?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }




}