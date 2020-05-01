package com.hsmnzaydn.waaperhd.ui.images

import com.basefy.base_mvp.BasePresenter
import com.basefy.base_mvp.BaseResponseCallBack
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class ImagesPresenter<V:ImagesContract.View> @Inject constructor(private val imageUseCase: ImageUseCase):
BasePresenter<V>(),ImagesContract.Presenter<V>{

    override fun getImages() {
        mvpView.showLoading()
        imageUseCase.getImages(object : BaseResponseCallBack<List<Image.ThumbNailImage>>(mvpView){
            override fun onSuccess(response: List<Image.ThumbNailImage>?) {
                super.onSuccess(response)
                mvpView.loadDataToList(response)
            }
        })
    }

}