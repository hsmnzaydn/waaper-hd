package com.hsmnzaydn.waaperhd.ui.images

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.basefy.base_mvp.BaseFragment
import com.basefy.core_utility.onInitGrid
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.databinding.ActivityHomeBinding
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
        imageAdapter.items = response?.let { it } ?: emptyList()
        imageAdapter.onItemClick { it, position, layoutId ->
            controller.navigate(ImageDetailFragment())
        }
    }

    override fun initUI() {

        binding = FragmentImagesBinding.inflate(layoutInflater)

        presenter.onAttach(this)

        imageAdapter = ImagesAdapter(activity!!)


        imageAdapter.onInitGrid(
            binding!!.fragmentImagesRecylerview,
            column = 2
        )


        presenter.getImages()
    }

    override fun againOpened() {
        presenter.getImages()
    }


}
