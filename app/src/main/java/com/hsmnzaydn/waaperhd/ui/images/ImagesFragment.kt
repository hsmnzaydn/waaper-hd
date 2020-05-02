package com.hsmnzaydn.waaperhd.ui.images

import android.os.Parcelable
import com.basefy.base_mvp.BaseFragment
import com.basefy.core_utility.onInitGrid
import com.basefy.core_utility.pagenation
import com.hsmnzaydn.waaperhd.databinding.FragmentImagesBinding
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.ui.adapters.ImagesAdapter
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.ui.image_detail.ImageDetailFragment
import javax.inject.Inject


class ImagesFragment : BaseFragment<FragmentImagesBinding>(), ImagesContract.View {
    @Inject
    lateinit var presenter: ImagesContract.Presenter<ImagesContract.View>


    private lateinit var imageAdapter: ImagesAdapter

    override fun loadDataToList(response: List<Image>?) {
        imageAdapter.items = response!!

        imageAdapter.updateList(binding!!.fragmentImagesRecylerview)

        imageAdapter.onItemClick { it, position, layoutId ->
          controller.navigate(ImageDetailFragment())
        }

    }

    override fun initUI() {
        binding = FragmentImagesBinding.inflate(layoutInflater)
        presenter.onAttach(this)

        initImageAdapter()

    }

    private fun initImageAdapter() {
        imageAdapter = ImagesAdapter(activity!!)
        imageAdapter.onInitGrid(
            binding!!.fragmentImagesRecylerview,
            column = 2
        )

        binding?.fragmentImagesRecylerview?.pagenation {
            presenter.getImages()
        }

        presenter.getImages()
    }

    override fun againOpened() {
      //  presenter.getImages()
    }


}
