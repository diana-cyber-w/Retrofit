package com.example.retrofit.data.di

import android.content.Context
import androidx.room.Room
import com.example.retrofit.data.storage.AppDatabase
import com.example.retrofit.data.storage.NewsDao
import com.example.retrofit.repository.DataRepository
import com.example.retrofit.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesDatabaseBuilder(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news"
        ).build()

    @Provides
    fun providesNewsDao(appDatabase: AppDatabase): NewsDao =
        appDatabase.getNewsDao()

    @Provides
    fun providesDataRepositoryImpl(newsDao: NewsDao): DataRepository =
        DataRepositoryImpl(newsDao = newsDao)
}