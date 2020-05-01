package com.basefy.base_mvp




open class BasePresenter<V : MvpView>
constructor() : MvpPresenter<V> {

    lateinit var mvpView: V


    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }
}

