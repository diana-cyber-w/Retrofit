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

    override suspend fun deleteNetworkNews(): List<News> {
        return withContext(Dispatchers.IO) {
            networkRepositoryImpl.deleteNetworkNews()
        }
    }

    override suspend fun getDataNews(): List<News> {
        return withContext(Dispatchers.IO) {
//            val savedNews = dataRepositoryImpl.getNews()
//            val articles = networkRepositoryImpl.getEverything(query = "",
//                fromDate = null,
//                toDate = null,
//                language = "ru",
//                sortBy = "")
//            val list: MutableList<News> = mutableListOf()
//
//            articles.forEach { item ->
//                val newItem = savedNews.find { news->
//                    item.title == news.title
//                }
//                if (newItem != null) {
//                    list.add(newItem)
//                }
//            }
//            return@withContext list
            dataRepositoryImpl.getNews()
        }
    }

    override suspend fun insertDataNews(news: News) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.insertNews()
        }
    }

    override suspend fun deleteDataNews(news: News) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.deleteNews(news)
        }
    }
}