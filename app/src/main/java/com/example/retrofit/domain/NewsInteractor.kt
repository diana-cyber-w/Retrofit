package com.example.retrofit.domain

interface NewsInteractor {
    suspend fun getNetworkNews(): List<News>

    suspend fun getDataNews(): List<News>

    suspend fun insertDataNews(news: News)

    suspend fun deleteDataNews(title: String)
}