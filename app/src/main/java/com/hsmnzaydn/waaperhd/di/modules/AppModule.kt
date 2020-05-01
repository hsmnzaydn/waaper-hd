package com.hsmnzaydn.waaperhd.di.modules

import android.content.Context
import com.hsmnzaydn.waaperhd.WaaperHDApp
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: WaaperHDApp): Context
}
