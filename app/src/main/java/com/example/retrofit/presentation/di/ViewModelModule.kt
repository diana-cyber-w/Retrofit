package com.example.retrofit.presentation.di

import com.example.retrofit.presentation.SharedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sharedViewModelModule = module {
    viewModel {
        SharedViewModel(interactor = get(), sharedPreferenceManager = get())
    }
}