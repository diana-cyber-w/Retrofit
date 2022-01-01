package com.example.retrofit.repository

import com.example.retrofit.data.storage.NewsDao
import com.example.retrofit.domain.News
import com.example.retrofit.domain.toNews
import com.example.retrofit.domain.toNewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepositoryImpl(private val newsDao: NewsDao) : DataRepository {
    override suspend fun getNews(): List<News> {
        return withContext(Dispatchers.IO) {
            newsDao.getAll().map { newsEntity -> newsEntity.toNews() }
        }
    }

    override suspend fun insertNews(vararg news: News) {
        withContext(Dispatchers.IO) {
            news
                .map { domainMews -> domainMews.toNewsEntity() }
                .forEach { newsEntity -> newsDao.insertNews(newsEntity) }
        }
    }

    override suspend fun deleteNews(vararg news: News) {
        withContext(Dispatchers.IO) {
            news
                .map { domainMews -> domainMews.toNewsEntity() }
                .forEach { newsEntity -> newsDao.deleteNews(newsEntity) }
        }
    }
}