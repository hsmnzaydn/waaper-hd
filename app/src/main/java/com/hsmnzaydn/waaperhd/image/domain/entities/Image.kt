package com.hsmnzaydn.waaperhd.image.domain.entities

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import com.basefy.base_mvp.RecyclerItem
import com.hsmnzaydn.waaperhd.ui.adapters.ImagesAdapter

sealed class Image(
    @LayoutRes layoutId: Int,
    comporeId: String,
    open var id: String? = "",
    open var imagePath: String? = ""
) : RecyclerItem(layoutId, comporeId) {

    data class ThumbNailImage(
        override var id: String?,
        override var imagePath: String?
    ) : Image(ImagesAdapter.ImageListViewHolder.ImageCell.LAYOUT_ID,id!!)

    class ImageDiffUtil(
        private val oldList: List<Image>,
        private val newList: List<Image>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}