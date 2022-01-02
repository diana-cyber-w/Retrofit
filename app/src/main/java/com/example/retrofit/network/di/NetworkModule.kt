package com.example.retrofit.network.di

import com.example.retrofit.network.RetrofitClient
import com.example.retrofit.repository.NetworkRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        RetrofitClient.getNewsApi()
    }

    single {
        NetworkRepositoryImpl(newsApi = get())
    }
}