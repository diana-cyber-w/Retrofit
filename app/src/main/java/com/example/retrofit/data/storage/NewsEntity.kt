package com.example.retrofit.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "click")
    val isIconClicked: Boolean,

    @ColumnInfo(name = "icon")
    val iconUrl: String,

    @ColumnInfo(name = "article")
    val articleUrl: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}