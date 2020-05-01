package com.hsmnzaydn.waaperhd.image.data.api

import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface ImageServices {

    @GET("photos")
    fun getImages():Observable<List<ImageResponse>>
}