package com.example.retrofit.domain.di

import com.example.retrofit.domain.NewsInteractor
import com.example.retrofit.domain.NewsInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<NewsInteractor> {
        NewsInteractorImpl(dataRepositoryImpl = get(), networkRepositoryImpl = get())
    }
}