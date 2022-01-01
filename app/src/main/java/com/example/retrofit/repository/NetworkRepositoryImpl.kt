package com.example.retrofit.repository

import com.example.retrofit.domain.News
import com.example.retrofit.domain.toNews
import com.example.retrofit.network.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl(private val newsApi: NewsApi) : NetworkRepository {

    private val emptyNews: List<News> = emptyList()

    override suspend fun getEverything(
        query: String?,
        fromDate: String?,
        toDate: String?,
        language: String?,
        sortBy: String
    ): List<News> {

        return withContext(Dispatchers.IO) {
            newsApi.getEverything(
                query = query,
                fromDate = fromDate,
                toDate = toDate,
                language = language,
                sortBy = sortBy
            ).articles.map { article -> article.toNews() }
        }
    }

    override suspend fun deleteNetworkNews(): List<News> {
        return emptyNews
    }
}