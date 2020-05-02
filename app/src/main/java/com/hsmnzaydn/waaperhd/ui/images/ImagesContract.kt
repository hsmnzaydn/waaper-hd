package com.hsmnzaydn.waaperhd.ui.images

import com.basefy.base_mvp.MvpPresenter
import com.basefy.base_mvp.MvpView
import com.hsmnzaydn.waaperhd.image.domain.entities.Image


//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
interface ImagesContract {

    interface View : MvpView {
        /**
         * Sunucudan gelen resimleri recylerviewa atmak için kullanılır
         */
        fun loadDataToList(oldItems: List<Image>?)

    }

    interface Presenter<V : View> : MvpPresenter<V> {

        /**
         * Ana ekrandaki resimleri çağırmak için kullanılır
         */
        fun getImages()
    }
}