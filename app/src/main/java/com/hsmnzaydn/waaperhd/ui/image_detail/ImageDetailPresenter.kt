package com.hsmnzaydn.waaperhd.ui.image_detail

import com.basefy.base_mvp.BasePresenter
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class ImageDetailPresenter<V:ImageDetailContract.View> @Inject constructor(private val imageUseCase: ImageUseCase):
BasePresenter<V>(),ImageDetailContract.Presenter<V>{

}