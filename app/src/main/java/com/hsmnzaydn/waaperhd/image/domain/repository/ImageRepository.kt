package com.hsmnzaydn.waaperhd.image.domain.repository

import com.basefy.core_network.CoreServiceCallback
import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse

interface ImageRepository{
    fun getImages(page:Int?,callback: CoreServiceCallback<List<ImageResponse>>)
    fun getImage(imageId:String,callback: CoreServiceCallback<ImageResponse>)
}