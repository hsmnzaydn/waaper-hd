package com.basefy.base_mvp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment


abstract class BaseFragment<T : ViewBinding> : DaggerFragment(), MvpView {

    private var baseActivity: BaseActivity? = null

    protected var binding: T? = null
        get() =  binding?.let { it }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding?.let {
            againOpened()
        }?: kotlin.run {
            initUI()
        }

        return binding?.root
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }


    override fun showLoading() {
        baseActivity!!.showLoading()
    }

    override fun hideLoading() {
        baseActivity!!.hideLoading()
    }

    override fun showDialogWithOutChoose(
        title: String,
        description: String,
        negativeButtonText: String?,
        onClickNegativeButton: () -> Unit
    ) {
        if (baseActivity != null) {
            baseActivity!!.showDialogWithOutChoose(
                title,
                description,
                negativeButtonText,
                onClickNegativeButton
            )
        }

    }

    override fun showInformation(text: String) {
        if (baseActivity != null) {
            baseActivity!!.showError(text)
        }

    }


    override fun showError(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showError(message)
        }
    }


    override fun hideSystemUI() {
        val decorView = activity!!.window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE

                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }


    override fun addScrollableToFullScreenView(statusBarColor: Int?) {
        baseActivity!!.addScrollableToFullScreenView(statusBarColor)
    }


    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}