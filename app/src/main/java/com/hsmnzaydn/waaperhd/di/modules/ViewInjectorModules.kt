package com.hsmnzaydn.waaperhd.di.modules


import com.hsmnzaydn.waaperhd.ui.home.HomeActivity
import com.hsmnzaydn.waaperhd.ui.image_detail.ImageDetailFragment
import com.hsmnzaydn.waaperhd.ui.images.ImagesFragment
import com.hsmnzaydn.waaperhd.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewInjectorModules {


    @ContributesAndroidInjector
    abstract fun homeActivityInjector(): HomeActivity


    @ContributesAndroidInjector
    abstract fun imagesFragmentInjector(): ImagesFragment


    @ContributesAndroidInjector
    abstract fun imagedetailFragmentInjector(): ImageDetailFragment


    @ContributesAndroidInjector
    abstract fun splashFragmentInjector(): SplashFragment

}
