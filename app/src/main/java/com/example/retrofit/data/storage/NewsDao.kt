package com.example.retrofit.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<NewsEntity>

    @Insert
    fun insertNews(news: NewsEntity)

    @Query("DELETE FROM news WHERE title = :title")
    fun deleteNews(title: String)
}