package com.example.retrofit.presentation.di

import com.example.retrofit.domain.NewsInteractor
import com.example.retrofit.presentation.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesSharedViewModel(
        interactor: NewsInteractor
    ): SharedViewModel =
        SharedViewModel(newsInteractor = interactor)
}