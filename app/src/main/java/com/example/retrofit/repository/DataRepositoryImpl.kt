package com.example.retrofit.repository

import com.example.retrofit.data.storage.NewsDao
import com.example.retrofit.domain.News
import com.example.retrofit.domain.toNews
import com.example.retrofit.domain.toNewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(private val newsDao: NewsDao) : DataRepository {
    override suspend fun getNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toNews() }
        }
    }

    override suspend fun insertNews(news: News) {
        withContext(Dispatchers.IO) {
            newsDao.insertNews(news.toNewsEntity())
        }
    }

    override suspend fun deleteNews(title: String) {
        withContext(Dispatchers.IO) {
            newsDao.deleteNews(title)
        }
    }
}