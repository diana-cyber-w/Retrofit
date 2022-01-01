package com.example.retrofit.repository

import com.example.retrofit.domain.News

interface NetworkRepository {
    suspend fun getEverything(
        query: String?,
        fromDate: String?,
        toDate: String?,
        language: String?,
        sortBy: String
    ): List<News>

    suspend fun deleteNetworkNews(): List<News>
}