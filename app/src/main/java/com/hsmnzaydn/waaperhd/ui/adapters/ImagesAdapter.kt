package com.hsmnzaydn.waaperhd.ui.adapters

import android.app.Activity
import android.view.ViewGroup
import com.basefy.base_mvp.BaseRecyclerAdapter
import com.basefy.base_mvp.BaseViewHolder
import com.basefy.core_utility.CoreImageloaderUtility
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.databinding.CellImageBinding
import com.hsmnzaydn.waaperhd.image.domain.entities.Image

class ImagesAdapter(
    private val activity:Activity
) : BaseRecyclerAdapter<Image, ImagesAdapter.ImageListViewHolder<Image>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)  =
        when(viewType){
            ImageListViewHolder.ImageCell.LAYOUT_ID -> ImageListViewHolder.ImageCell(
                activity,
                parent
            )
            else -> ImageListViewHolder.ImageCell(
                activity,
                parent
            )
        } as ImageListViewHolder<Image>



    sealed class ImageListViewHolder<T : Image>(parent: ViewGroup, layoutId: Int) :
        BaseViewHolder<T>(parent, layoutId) {

        class ImageCell constructor(
            private val activity:Activity,
            parent: ViewGroup
        ) :
            ImageListViewHolder<Image.ThumbNailImage>(
                parent,
                LAYOUT_ID
            ) {

            lateinit var binding: CellImageBinding

            override fun bindItem(item: Image.ThumbNailImage) {
                binding=  CellImageBinding.bind(itemView)

                CoreImageloaderUtility.imageLoaderWithCacheFitWithLoadingWithCircle(activity,item.imagePath,binding.cellImageThumbnailImageView,binding.cellImageLoadingProgressBar )
            }

            companion object {
                var LAYOUT_ID = R.layout.cell_image
            }

        }

    }


}