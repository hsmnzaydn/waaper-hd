package com.hsmnzaydn.waaperhd.image.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.basefy.base_mvp.BaseResponseCallBack
import com.basefy.core_network.CoreServiceCallback
import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.repository.ImageRepository
import toImageDetail
import toImageThumbNail
import javax.inject.Inject

//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
class ImageUseCase @Inject constructor(private val imageRepository: ImageRepository) {



    fun getImages(page:Int?,callback: BaseResponseCallBack<List<Image.ThumbNailImage>>) {
        imageRepository.getImages(page,object : CoreServiceCallback<List<ImageResponse>> {
            override fun onSuccess(response: List<ImageResponse>?) {
                callback.onSuccess(response?.map {
                    it.toImageThumbNail()
                })
            }

            override fun onError(errorCode: Int, errorMessage: String) {
               callback.onError(errorCode, errorMessage)
            }

        })
    }

    fun getImage(imageId:String,callback: BaseResponseCallBack<Image.ImageDetail>){
        imageRepository.getImage(imageId,object : CoreServiceCallback<ImageResponse>{
            override fun onSuccess(response: ImageResponse?) {
                callback.onSuccess(response?.toImageDetail())
            }

            override fun onError(errorCode: Int, errorMessage: String) {
                callback.onError(errorCode, errorMessage)
            }

        })
    }

    fun searchImage(page: Int?,query:String,callback: BaseResponseCallBack<List<Image.ThumbNailImage>>){
        imageRepository.searchImages(page,query,object :CoreServiceCallback<List<ImageResponse>>{
            override fun onSuccess(response: List<ImageResponse>?) {
                callback.onSuccess(response?.map { it.toImageThumbNail() })
            }

            override fun onError(errorCode: Int, errorMessage: String) {
                callback.onError(errorCode, errorMessage)
            }

        })
    }
}