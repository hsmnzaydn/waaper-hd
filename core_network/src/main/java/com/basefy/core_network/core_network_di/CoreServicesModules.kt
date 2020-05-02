package com.basefy.core_network.core_network_di

import android.content.Context
import android.content.SharedPreferences
import com.basefy.core_local.CoreLocalHelper
import com.basefy.core_local.core_local_di.CoreLocalModules
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module(
    includes = [CoreLocalModules::class]
)
class CoreServicesModules {

    /**
     * Dagger 2 için retrofiti provide eder
     * @param context: Uygulamanın contextini içerir
     */
    @Provides
    @Singleton
    fun provideRetrofitClient(context: Context, sharedPreferences: CoreLocalHelper): Retrofit {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(context.cacheDir, cacheSize.toLong())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder().baseUrl(com.basefy.core_network.BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    }


}