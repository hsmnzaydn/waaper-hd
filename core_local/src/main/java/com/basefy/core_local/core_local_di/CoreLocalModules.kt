package com.basefy.core_local.core_local_di

import android.content.Context
import com.basefy.core_local.CoreLocalHelper
import com.basefy.core_local.CoreLocalHelperImp
import com.google.gson.Gson

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoreLocalModules {

    /**
     * SharedPref'i provide etmek için kullanılır
     * @param context: Uygulamanın contextidir
     * @param gson: Strinleri classlara maplemek için kullanılır
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context, gson: Gson): CoreLocalHelper =
        CoreLocalHelperImp(context, gson)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

}