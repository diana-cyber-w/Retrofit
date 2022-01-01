package com.example.retrofit.data.storage

import androidx.room.*

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert
    fun insertNews(news: NewsEntity)

    @Delete
    fun deleteNews(news: NewsEntity)
}