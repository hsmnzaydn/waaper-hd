package com.hsmnzaydn.waaperhd.image.domain.entities

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.basefy.base_mvp.RecyclerItem
import com.hsmnzaydn.waaperhd.ui.adapters.ImagesAdapter

abstract class Image(
    override val layoutId: Int,
    open var id: String? = "",
    open var imagePath: String? = ""
) : RecyclerItem {

    data class ThumbNailImage(
        override var id: String?,
        override var imagePath: String?
    ) : Image(ImagesAdapter.ImageListViewHolder.ImageCell.LAYOUT_ID!!)

    data class ImageDetail(
        var id: String?,
        var imagePath: String?
    )
}