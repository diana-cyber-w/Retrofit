package com.example.retrofit.repository

import com.example.retrofit.domain.News

interface DataRepository {
    suspend fun getNews(): List<News>

    suspend fun insertNews(vararg news: News)

    suspend fun deleteNews(vararg news: News)
}