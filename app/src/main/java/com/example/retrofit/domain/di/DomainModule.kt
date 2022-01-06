package com.example.retrofit.domain.di

import com.example.retrofit.domain.NewsInteractor
import com.example.retrofit.domain.NewsInteractorImpl
import com.example.retrofit.repository.DataRepositoryImpl
import com.example.retrofit.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideNewsInteractor(
        dataRepositoryImpl: DataRepositoryImpl,
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NewsInteractor =
        NewsInteractorImpl(
            dataRepositoryImpl = dataRepositoryImpl,
            networkRepositoryImpl = networkRepositoryImpl
        )
}