package com.hsmnzaydn.waaperhd.ui.image_detail

import com.basefy.base_mvp.MvpPresenter
import com.basefy.base_mvp.MvpView

//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
interface ImageDetailContract {

    interface View : MvpView {

    }

    interface Presenter<V : View> : MvpPresenter<V> {

    }
}