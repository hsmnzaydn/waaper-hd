package com.hsmnzaydn.waaperhd.ui.image_detail

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
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
import java.io.File
import java.io.FileOutputStream
import java.util.*
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
                    var filename = UUID.randomUUID()
                    var file =
                        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath().toString() + File.separator + "folderName")
                    if (!file.exists()) {
                        file.mkdir()
                    }
                    file =
                        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath().toString() + File.separator + "folderName" + File.separator + filename + ".jpg")
                    try {
                        val out = FileOutputStream(file)
                        it.compress(Bitmap.CompressFormat.PNG, 90, out)
                        out.flush()
                        out.close()

                        val intentShareFile = Intent(Intent.ACTION_SEND)

                        if (file.exists()) {
                            intentShareFile.type = "application/pdf"
                            intentShareFile.putExtra(
                                Intent.EXTRA_STREAM,
                                Uri.parse("file://${file.absolutePath}")
                            )
                            intentShareFile.putExtra(
                                Intent.EXTRA_SUBJECT,
                                "Sharing File..."
                            )
                            intentShareFile.putExtra(
                                Intent.EXTRA_TEXT,
                                "Sharing File..."
                            )
                            startActivity(
                                Intent.createChooser(
                                    intentShareFile,
                                    "Share File"
                                )
                            )
                        }
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                        Log.e("ImageViewZoom", exception.message)

                    }
                })
            Dexter.withContext(activity)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener, MultiplePermissionsListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {



                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        Log.d("veri","veri")
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Log.d("veri","veri")
                    }

                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {

                    }
                })

        }

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
