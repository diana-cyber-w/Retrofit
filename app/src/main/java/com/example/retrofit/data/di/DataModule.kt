package com.example.retrofit.data.di

import androidx.room.Room
import com.example.retrofit.repository.DataRepositoryImpl
import com.example.retrofit.data.storage.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "news"
        ).build()
    }

    single {
        get<AppDatabase>().getNewsDao()
    }

    single {
        DataRepositoryImpl(newsDao = get())
    }
}