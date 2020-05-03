package com.hsmnzaydn.waaperhd.ui.image_detail

import android.Manifest
import android.os.Bundle
import com.basefy.base_mvp.BaseFragment
import com.basefy.core_utility.CoreCommonUtils
import com.basefy.core_utility.CoreImageloaderUtility
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.databinding.FragmentImageDetailBinding
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.utility.BundleConstant
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject


class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(), ImageDetailContract.View {

    @Inject
    lateinit var presenter: ImageDetailContract.Presenter<ImageDetailContract.View>
    lateinit var rxPermissions: RxPermissions

    override fun initUI() {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)
        presenter.onAttach(this)
        rxPermissions = RxPermissions(this)

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


        binding!!.fragmentImageDetailDownloadButton.setOnClickListener {
            showLoading()
            CoreImageloaderUtility.getImageBitmap(
                activity!!,
                image!!.imagePath!!,
                fileCallback = {
                    if (rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        CoreCommonUtils.saveImage(
                            activity!!,
                            it,
                            getString(R.string.app_name),
                            saveImageCallback = {
                                hideLoading()
                                showInformation(getString(R.string.image_detail_downloaded_image))
                            })
                    } else {
                        hideLoading()
                        rxPermissions
                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe { granted: Boolean ->
                                if (granted) {
                                    showLoading()
                                    CoreCommonUtils.saveImage(
                                        activity!!,
                                        it,
                                        getString(R.string.app_name),
                                        saveImageCallback = {
                                            hideLoading()
                                            showInformation(getString(R.string.image_detail_downloaded_image))
                                        })
                                } else {
                                    showError(getString(R.string.warning_permission))
                                }
                            }
                    }


                })


        }

        binding!!.fragmentImageDetailSetButton.setOnClickListener {
            /*    val wallpaperManager =
                    WallpaperManager.getInstance(activity)
                wallpaperManager.setBitmap(it)*/
        }
    }


    companion object {
        fun getImageDetailInstance(imageId: String, imageUrl: String): ImageDetailFragment {
            var bundle = Bundle()
            bundle.putString(BundleConstant.IMAGE_ID_BUNDLE, imageId)
            bundle.putString(BundleConstant.IMAGE_URL_BUNDLE, imageUrl)
            var fragment = ImageDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}
