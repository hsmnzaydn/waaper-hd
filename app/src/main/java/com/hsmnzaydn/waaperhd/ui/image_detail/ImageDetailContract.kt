package com.hsmnzaydn.waaperhd.ui.image_detail

import com.basefy.base_mvp.MvpPresenter
import com.basefy.base_mvp.MvpView
import com.hsmnzaydn.waaperhd.image.domain.entities.Image

//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
interface ImageDetailContract {

    interface View : MvpView {
        /**
         * Gelen resimi set etmek için kullanılır
         */
        fun setImageData(image: Image.ImageDetail?)

    }

    interface Presenter<V : View> : MvpPresenter<V> {
        /**
         * Resim detayı çağırır
         */
        fun getImage(string: String?)

    }
}