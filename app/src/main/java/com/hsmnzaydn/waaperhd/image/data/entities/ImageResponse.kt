package com.hsmnzaydn.waaperhd.image.data.entities


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id")
    val imageId: String,
    @SerializedName("imagePath")
    val imagePath:String

)