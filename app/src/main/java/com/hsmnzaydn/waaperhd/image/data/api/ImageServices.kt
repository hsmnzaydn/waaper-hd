package com.hsmnzaydn.waaperhd.image.data.api

import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ImageServices {

    @GET("photos")
    fun getImages(@Query("page") page:Int? = 1):Observable<List<ImageResponse>>
}