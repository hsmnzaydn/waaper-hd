package com.hsmnzaydn.waaperhd.ui.home

import com.basefy.base_mvp.MvpPresenter
import com.basefy.base_mvp.MvpView
import com.hsmnzaydn.waaperhd.image.domain.entities.Image


//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
interface HomeContract {

    interface View : MvpView {

    }

    interface Presenter<V : View> : MvpPresenter<V> {


    }
}