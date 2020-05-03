package com.hsmnzaydn.waaperhd.ui.splash

import com.basefy.base_mvp.BasePresenter
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class SplashPresenter<V : SplashContract.View> @Inject constructor() :
    BasePresenter<V>(), SplashContract.Presenter<V> {
    override fun decideUI() {
        Timer("SettingUp",false).schedule(1500){
            mvpView.openImageFragment()
        }
    }

}