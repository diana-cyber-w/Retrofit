package com.example.retrofit

import android.app.Application
import com.example.retrofit.data.di.dataModule
import com.example.retrofit.domain.di.domainModule
import com.example.retrofit.network.di.networkModule
import com.example.retrofit.presentation.di.sharedViewModelModule
import com.example.retrofit.utils.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                dataModule,
                domainModule,
                networkModule,
                utilsModule,
                sharedViewModelModule
            )
        }
    }
}