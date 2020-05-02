package com.hsmnzaydn.waaperhd.ui.image_detail

import android.os.Bundle
import com.basefy.base_mvp.BaseFragment
import com.hsmnzaydn.waaperhd.databinding.FragmentImageDetailBinding
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.utility.BundleConstant
import javax.inject.Inject

class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(), ImageDetailContract.View {

    @Inject
    lateinit var presenter: ImageDetailContract.Presenter<ImageDetailContract.View>


    override fun initUI() {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)
        presenter.onAttach(this)


        presenter.getImage(arguments?.getString(BundleConstant.IMAGE_ID_BUNDLE))

        binding?.fragmentImageDetailToolbar!!.onClickBackIcon(controller)

    }

    override fun setImageData(image: com.hsmnzaydn.waaperhd.image.domain.entities.Image.ImageDetail?) {
       /* CoreImageloaderUtility.imageLoaderWithCacheFit(
            activity!!, image?.imagePath,
            binding!!.fragmentImageDetailImageImageView
        )*/

    }


    companion object {
        fun getImageDetailInstance(imageId: String): ImageDetailFragment {
            var bundle = Bundle()
            bundle.putString(BundleConstant.IMAGE_ID_BUNDLE, imageId)
            var fragment = ImageDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
