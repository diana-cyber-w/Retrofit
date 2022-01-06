package com.example.retrofit.utils.di

import android.content.Context
import com.example.retrofit.utils.prefs.SharedPreferenceImpl
import com.example.retrofit.utils.prefs.SharedPreferenceManager
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun providesSharedPreferenceManager(context: Context): SharedPreferenceManager =
        SharedPreferenceImpl(context)
}