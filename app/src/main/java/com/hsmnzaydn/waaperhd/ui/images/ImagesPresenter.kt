package com.hsmnzaydn.waaperhd.ui.images

import androidx.lifecycle.MutableLiveData
import com.basefy.base_mvp.BasePresenter
import com.basefy.base_mvp.BaseResponseCallBack
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import javax.inject.Inject

class ImagesPresenter<V:ImagesContract.View> @Inject constructor(private val imageUseCase: ImageUseCase):
BasePresenter<V>(),ImagesContract.Presenter<V>{


    var imageList:MutableList<Image> = ArrayList<Image>()
    var searchImageList:MutableList<Image> = ArrayList<Image>()

    var page:Int = 0
    var pageSearch:Int = 0
    override fun getImages() {

        if(page == 0){
            mvpView.showLoading()
        }else{
            mvpView.showBottomLoadin()
        }
        imageUseCase.getImages(page,object : BaseResponseCallBack<List<Image.ThumbNailImage>>(mvpView){
            override fun onSuccess(response: List<Image.ThumbNailImage>?) {
                super.onSuccess(response)
                imageList.addAll(response!!)
                mvpView.loadDataToList(imageList)
                page++
            }
        })
    }

    override fun searchImages(it: String) {
        if(pageSearch == 0){
            mvpView.showLoading()
        }else{
            mvpView.showBottomLoadin()
        }

        imageUseCase.searchImage(pageSearch,it,object :BaseResponseCallBack<List<Image.ThumbNailImage>>(mvpView){
            override fun onSuccess(response: List<Image.ThumbNailImage>?) {
                super.onSuccess(response)
                searchImageList.addAll(response!!)
                mvpView.loadDataToList(searchImageList)
            }
        })
    }

}