package com.example.retrofit.network.di

import com.example.retrofit.repository.NetworkRepositoryImpl
import com.example.retrofit.network.RetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single {
        RetrofitClient.getNewsApi()
    }

    single {
        NetworkRepositoryImpl(newsApi = get())
    }
}