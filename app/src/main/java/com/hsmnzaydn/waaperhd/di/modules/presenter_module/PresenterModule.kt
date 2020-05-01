package com.hsmnzaydn.waaperhd.di.modules.presenter_module


import com.hsmnzaydn.waaperhd.image.domain.usecase.ImageUseCase
import com.hsmnzaydn.waaperhd.ui.home.HomeContract
import com.hsmnzaydn.waaperhd.ui.home.HomePresenter
import com.hsmnzaydn.waaperhd.ui.images.ImagesContract
import com.hsmnzaydn.waaperhd.ui.images.ImagesPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {


    @Provides
    @Singleton
    fun provideHomeModule(imageUseCase:ImageUseCase): HomeContract.Presenter<HomeContract.View> {
        return HomePresenter(imageUseCase)
    }

    @Provides
    @Singleton
    fun provideImagesModule(imageUseCase:ImageUseCase): ImagesContract.Presenter<ImagesContract.View>
    {
        return ImagesPresenter(imageUseCase)
    }

}

