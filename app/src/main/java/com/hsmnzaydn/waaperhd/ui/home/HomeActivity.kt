package com.hsmnzaydn.waaperhd.ui.home

import android.os.Bundle
import com.basefy.base_mvp.BaseActivity
import com.basefy.core_utility.onInitGrid
import com.hsmnzaydn.waaperhd.databinding.ActivityHomeBinding
import com.hsmnzaydn.waaperhd.image.domain.entities.Image
import com.hsmnzaydn.waaperhd.ui.adapters.ImagesAdapter
import com.hsmnzaydn.waaperhd.ui.images.ImagesFragment
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    private lateinit var binding: ActivityHomeBinding
    @Inject
    lateinit var presenter: HomeContract.Presenter<HomeContract.View>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onAttach(this)

        binding.activityHomeFragmentView.bind(this)

        binding.activityHomeFragmentView.navigate(ImagesFragment())

    }



}