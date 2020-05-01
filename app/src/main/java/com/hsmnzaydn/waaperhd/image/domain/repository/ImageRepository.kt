package com.hsmnzaydn.waaperhd.image.domain.repository

import com.basefy.core_network.CoreServiceCallback
import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse

interface ImageRepository{
    fun getImages(callback: CoreServiceCallback<List<ImageResponse>>)
}