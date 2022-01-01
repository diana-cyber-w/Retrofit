package com.example.retrofit.network.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val resultsNumber: Long?,
    @SerializedName("articles")
    val articles: List<Article>
)

data class Article(
    @SerializedName("source")
    val source: Source,
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val articleUrl: String?,
    @SerializedName("urlToImage")
    val previewUrl: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)