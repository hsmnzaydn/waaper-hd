package com.hsmnzaydn.waaperhd



import com.hsmnzaydn.waaperhd.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class WaaperHDApp : DaggerApplication() {

    private val appComponent: AndroidInjector<WaaperHDApp> by lazy {
        DaggerApplicationComponent
            .builder()
            .create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


}
