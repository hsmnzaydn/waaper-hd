package com.hsmnzaydn.waaperhd.ui.splash

import androidx.fragment.app.Fragment
import com.basefy.base_mvp.BaseFragment
import com.hsmnzaydn.waaperhd.R
import com.hsmnzaydn.waaperhd.databinding.FragmentSplashBinding
import com.hsmnzaydn.waaperhd.ui.controller
import com.hsmnzaydn.waaperhd.ui.images.ImagesFragment
import javax.inject.Inject

class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashContract.View {
    @Inject
    lateinit var presenter: SplashContract.Presenter<SplashContract.View>

    override fun openImageFragment() {
        controller.navigate(ImagesFragment())
    }

    override fun initUI() {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        presenter.onAttach(this)
        presenter.decideUI()
    }

    override fun againOpened() {

    }

    companion object{
        fun getSplashInstance():Fragment{
            var splashInstance = SplashFragment()
            return splashInstance
        }
    }
}
