package com.hsmnzaydn.waaperhd.ui.home

import com.basefy.base_mvp.BasePresenter
import com.basefy.base_mvp.BaseResponseCallBack
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class HomePresenter<V : HomeContract.View> @Inject constructor(private val imageUseCase: ImageUseCase) :
    BasePresenter<V>(), HomeContract.Presenter<V> {



}