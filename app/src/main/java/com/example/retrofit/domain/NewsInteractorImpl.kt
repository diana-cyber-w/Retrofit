package com.example.retrofit.domain

import com.example.retrofit.repository.DataRepositoryImpl
import com.example.retrofit.repository.NetworkRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsInteractorImpl(
    private val networkRepositoryImpl: NetworkRepositoryImpl,
    private val dataRepositoryImpl: DataRepositoryImpl
) : NewsInteractor {

    override suspend fun getNetworkNews(): List<News> {
        return withContext(Dispatchers.IO) {
            networkRepositoryImpl.getEverything(
                query = "Политика",
                fromDate = null,
                toDate = null,
                language = "ru",
                sortBy = ""
            )
        }
    }

    override suspend fun getDataNews(): List<News> {
        return withContext(Dispatchers.IO) {
            dataRepositoryImpl.getNews()
        }
    }

    override suspend fun insertDataNews(news: News) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.insertNews(news)
        }
    }

    override suspend fun deleteDataNews(title: String) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.deleteNews(title)
        }
    }

}