package com.hsmnzaydn.waaperhd.ui.image_detail

import android.Manifest
import android.app.WallpaperManager
import android.os.Bundle
import android.util.Log
import com.basefy.base_mvp.BaseFragment
import com.basefy.core_utility.CoreImageloaderUtility
import com.hsmnzaydn.waaperhd.databinding.FragmentImageDetailBinding
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.utility.BundleConstant
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_image_detail.*
import javax.inject.Inject


class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(), ImageDetailContract.View {

    @Inject
    lateinit var presenter: ImageDetailContract.Presenter<ImageDetailContract.View>


    override fun initUI() {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)
        presenter.onAttach(this)


        presenter.getImage(arguments?.getString(BundleConstant.IMAGE_ID_BUNDLE))
        binding?.fragmentImageDetailToolbar!!.onClickBackIcon(controller)

        CoreImageloaderUtility.imageLoaderWithCacheFitWithLoading(
            activity!!, arguments?.getString(BundleConstant.IMAGE_URL_BUNDLE),
            binding!!.fragmentDetailImageViewZoom,
            binding!!.fragmentImageDetailProgressbar
        )

    }

    override fun setImageData(image: com.hsmnzaydn.waaperhd.image.domain.entities.Image.ImageDetail?) {
        CoreImageloaderUtility.imageLoaderWithCacheFitWithLoading(
            activity!!, image?.imagePath,
            binding!!.fragmentDetailImageViewZoom,
            binding!!.fragmentImageDetailProgressbar
        )

        fragment_image_detail_floatActionButton.setOnClickListener {

            CoreImageloaderUtility.getImageBitmap(
                activity!!,
                image!!.imagePath!!,
                fileCallback = {
                    val wallpaperManager =
                        WallpaperManager.getInstance(activity)
                    wallpaperManager.setBitmap(it)
                })

        }
    }

    companion object {
        fun getImageDetailInstance(imageId: String,imageUrl:String): ImageDetailFragment {
            var bundle = Bundle()
            bundle.putString(BundleConstant.IMAGE_ID_BUNDLE, imageId)
            bundle.putString(BundleConstant.IMAGE_URL_BUNDLE,imageUrl)
            var fragment = ImageDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
