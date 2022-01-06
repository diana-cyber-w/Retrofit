package com.example.retrofit.network.di

import com.example.retrofit.network.NewsApi
import com.example.retrofit.network.RetrofitClient
import com.example.retrofit.repository.NetworkRepository
import com.example.retrofit.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun providesNewsApi(): NewsApi = RetrofitClient.getNewsApi()

    @Provides
    fun providesNetworkRepositoryImpl(
        newsApi: NewsApi
    ): NetworkRepository =
        NetworkRepositoryImpl(newsApi = newsApi)
}