package com.basefy.base_mvp

import com.basefy.core_network.CoreServiceCallback


abstract class BaseResponseCallBack<R> constructor(
    private val mvpView: MvpView?
) : CoreServiceCallback<R> {

    override fun onSuccess(response: R?) {
        mvpView.let { it?.hideLoading() }
    }

    override fun onError(errorCode: Int, errorMessage: String) {
        mvpView?.hideLoading()
        mvpView?.showError(errorMessage)

    }
}