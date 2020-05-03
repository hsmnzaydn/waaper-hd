package com.hsmnzaydn.waaperhd.ui.splash

import com.basefy.base_mvp.MvpPresenter
import com.basefy.base_mvp.MvpView

//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
interface SplashContract {

    interface View : MvpView {
        /**
         * Image fragmenti açar
         */
        fun openImageFragment()

    }

    interface Presenter<V : View> : MvpPresenter<V> {
        /**
         * Gidilecek UI'yı belirler
         */
        fun decideUI()

    }
}