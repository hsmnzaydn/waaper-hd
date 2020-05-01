package com.hsmnzaydn.waaperhd.di.components


import com.hsmnzaydn.waaperhd.di.modules.AppModule
import com.hsmnzaydn.waaperhd.di.modules.ViewInjectorModules
import com.hsmnzaydn.waaperhd.di.modules.presenter_module.PresenterModule
import com.basefy.core_local.core_local_di.CoreLocalModules
import com.basefy.core_network.core_network_di.CoreServicesModules
import com.hsmnzaydn.waaperhd.WaaperHDApp
import com.hsmnzaydn.waaperhd.di.modules.image_module.ImageModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        CoreServicesModules::class,
        CoreLocalModules::class,
        ViewInjectorModules::class,
        PresenterModule::class,
        ImageModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<WaaperHDApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WaaperHDApp>()

}