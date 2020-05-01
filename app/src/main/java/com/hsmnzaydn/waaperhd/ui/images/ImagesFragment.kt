package com.hsmnzaydn.waaperhd.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ImagesFragment : BaseFragment(), ImagesContract.View {
    @Inject
    lateinit var presenter: ImagesContract.Presenter<ImagesContract.View>

    private lateinit var binding: FragmentImagesBinding

    private lateinit var imageAdapter:ImagesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImagesBinding.inflate(layoutInflater)

        presenter.onAttach(this)


        imageAdapter = ImagesAdapter(activity!!)


        imageAdapter.onInitGrid(
            binding.fragmentImagesRecylerview,
            column = 2
        )

        presenter.getImages()

        return binding.root
    }



    override fun loadDataToList(response: List<Image>?) {
        var response = response
        imageAdapter.items = response?.let { it } ?: emptyList()

        imageAdapter.onItemClick {it,position,layoutId ->
            controller.navigate(ImageDetailFragment())
        }
    }


}
