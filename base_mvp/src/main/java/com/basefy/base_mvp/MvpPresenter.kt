package com.basefy.base_mvp

interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)
}
