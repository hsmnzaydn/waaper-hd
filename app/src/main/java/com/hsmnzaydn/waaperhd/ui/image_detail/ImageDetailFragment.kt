package com.hsmnzaydn.waaperhd.ui.image_detail

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basefy.base_mvp.BaseFragment
import com.hsmnzaydn.waaperhd.databinding.FragmentImageDetailBinding
import com.hsmnzaydn.waaperhd.databinding.FragmentImagesBinding
import com.hsmnzaydn.waaperhd.utility.BundleConstant
import javax.inject.Inject

class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(), ImageDetailContract.View {

    @Inject
    lateinit var presenter: ImageDetailContract.Presenter<ImageDetailContract.View>

    override fun initUI() {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)
        presenter.onAttach(this)


        presenter.getImage(arguments?.getString(BundleConstant.IMAGE_ID_BUNDLE))

    }

    companion object{
        fun getImageDetailInstance( imageId:String): ImageDetailFragment{
            var bundle = Bundle()
            bundle.putString(BundleConstant.IMAGE_ID_BUNDLE,imageId)
            var fragment = ImageDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
