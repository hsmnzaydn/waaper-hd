package com.hsmnzaydn.waaperhd.ui.image_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.basefy.base_mvp.BaseFragment
import com.hsmnzaydn.waaperhd.databinding.FragmentImageDetailBinding
import javax.inject.Inject

class ImageDetailFragment : BaseFragment(), ImageDetailContract.View {
    @Inject
    lateinit var presenter: ImageDetailContract.Presenter<ImageDetailContract.View>
    private lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageDetailBinding.inflate(layoutInflater)


        presenter.onAttach(this)

        return binding.root
    }
}
