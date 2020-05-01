package com.hsmnzaydn.waaperhd.image.domain.usecase

import com.basefy.base_mvp.BaseResponseCallBack
import com.basefy.core_network.CoreServiceCallback
import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.image.domain.repository.ImageRepository
import toImageThumbNail
import javax.inject.Inject

//TODO: TÜM FONKSİYONLARIN ÜSTÜNE YORUM SATIRI KOYMAYI UNUTMA !!!!
class ImageUseCase @Inject constructor(private val imageRepository: ImageRepository) {


    fun getImages(callback: BaseResponseCallBack<List<Image.ThumbNailImage>>) {
        imageRepository.getImages(object : CoreServiceCallback<List<ImageResponse>> {
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
}