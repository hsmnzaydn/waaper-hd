package com.hsmnzaydn.waaperhd.image.data.repository

import com.basefy.core_network.CoreBaseServicesImp
import com.basefy.core_network.CoreServiceCallback
import com.hsmnzaydn.waaperhd.image.data.api.ImageServices
import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import retrofit2.Retrofit
import com.hsmnzaydn.waaperhd.image.domain.repository.ImageRepository

class ImageRepositoryImpl(private val retrofit: Retrofit) : CoreBaseServicesImp(retrofit),
    ImageRepository {

    fun getImageServices(): ImageServices = retrofit.create(ImageServices::class.java)


    override fun getImages(page:Int?,callback: CoreServiceCallback<List<ImageResponse>>) {
        getRequest(callback){
            getImageServices().getImages(page)
        }
    }

    override fun searchImages(
        page: Int?,
        query: String,
        callback: CoreServiceCallback<List<ImageResponse>>
    ) {
        getRequest(callback){
            getImageServices().searchImage(page, query)
        }
    }

    override fun getImage(imageId: String, callback: CoreServiceCallback<ImageResponse>) {
        getRequest(callback){
            getImageServices().getImage(imageId)
        }
    }

}