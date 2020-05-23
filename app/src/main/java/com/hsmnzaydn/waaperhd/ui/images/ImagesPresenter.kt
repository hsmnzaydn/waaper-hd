package com.hsmnzaydn.waaperhd.ui.images

import androidx.lifecycle.MutableLiveData
import com.basefy.base_mvp.BasePresenter
import com.basefy.base_mvp.BaseResponseCallBack
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class ImagesPresenter<V : ImagesContract.View> @Inject constructor(private val imageUseCase: ImageUseCase) :
    BasePresenter<V>(), ImagesContract.Presenter<V> {


    lateinit var imageList: MutableList<Image>

    override fun getImages(pageNumber: Int?) {
        if (pageNumber == 0) {
            mvpView.showLoading()
            imageList = ArrayList<Image>()
        } else {
            mvpView.showBottomLoadin()
        }
        imageUseCase.getImages(pageNumber,
            object : BaseResponseCallBack<List<Image.ThumbNailImage>>(mvpView) {
                override fun onSuccess(response: List<Image.ThumbNailImage>?) {
                    super.onSuccess(response)
                    imageList.addAll(response!!)
                    mvpView.loadDataToList(imageList)
                }
            })
    }

    override fun searchImages(pageNumber: Int, it: String) {
        if (pageNumber == 0) {
            mvpView.showLoading()
            imageList = ArrayList<Image>()
        } else {
            mvpView.showBottomLoadin()
        }

        imageUseCase.searchImage(
            pageNumber,
            it,
            object : BaseResponseCallBack<List<Image.ThumbNailImage>>(mvpView) {
                override fun onSuccess(response: List<Image.ThumbNailImage>?) {
                    super.onSuccess(response)
                    imageList.addAll(response!!)
                    mvpView.loadDataToList(response)

                }
            })
    }

}