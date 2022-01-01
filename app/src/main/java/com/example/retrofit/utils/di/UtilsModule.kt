package com.example.retrofit.utils.di

import com.example.retrofit.utils.prefs.SharedPreferenceImpl
import com.example.retrofit.utils.prefs.SharedPreferenceManager
import org.koin.dsl.module

val utilsModule = module {
    single<SharedPreferenceManager> {
        SharedPreferenceImpl(context = get())
    }
}