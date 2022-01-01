package com.example.retrofit.domain

data class News(
    val title: String,
    val author: String,
    val text: String,
    val date: String,
    var isIconClicked: Boolean,
    val iconUrl: String,
    val articleUrl: String
)
