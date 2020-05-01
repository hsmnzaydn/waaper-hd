package com.hsmnzaydn.waaperhd.di.modules.image_module

import com.hsmnzaydn.waaperhd.image.data.repository.ImageRepositoryImpl
import com.hsmnzaydn.waaperhd.image.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageRepository(retrofit: Retrofit): ImageRepository {
        return ImageRepositoryImpl(retrofit)
    }
}