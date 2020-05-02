package com.hsmnzaydn.waaperhd.ui.image_detail

import com.basefy.base_mvp.BasePresenter
import com.basefy.base_mvp.BaseResponseCallBack
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class ImageDetailPresenter<V : ImageDetailContract.View> @Inject constructor(private val imageUseCase: ImageUseCase) :
    BasePresenter<V>(), ImageDetailContract.Presenter<V> {
    override fun getImage(string: String?) {
        mvpView.showLoading()
        string?.let {
            imageUseCase.getImage(it,object :BaseResponseCallBack<Image.ImageDetail>(mvpView){
                override fun onSuccess(response: Image.ImageDetail?) {
                    super.onSuccess(response)
                }
            })
        }
    }

}