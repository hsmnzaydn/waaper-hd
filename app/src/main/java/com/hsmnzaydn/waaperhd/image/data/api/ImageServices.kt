package com.hsmnzaydn.waaperhd.image.data.api

import com.hsmnzaydn.waaperhd.image.data.entities.ImageResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ImageServices {

    @GET("images")
    fun getImages(@Query("page") page:Int? = 1):Single<List<ImageResponse>>

    @GET("images/{id}")
    fun getImage(@Path("id") imageId:String):Single<ImageResponse>
}