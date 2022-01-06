package com.example.retrofit.domain

import com.example.retrofit.repository.DataRepository
import com.example.retrofit.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val networkRepositoryImpl: NetworkRepository,
    private val dataRepositoryImpl: DataRepository
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