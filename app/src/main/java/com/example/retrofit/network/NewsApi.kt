package com.example.retrofit.network

import com.example.retrofit.network.dto.NewsResponse
import com.example.retrofit.network.dto.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("sources")
    fun getSource(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): SourceResponse

    @GET("sources")
    suspend fun getSourcesByLanguage(
        @Query("language") language: String,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): SourceResponse

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("q") query: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String?,
        @Query("from") fromDate: String?,
        @Query("to") toDate: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String,
        @Query("page") pageNumber: Int = 1,
        @Query("sources") sources: String = "Lenta",
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): NewsResponse

    @GET("everything")
    fun getEverythingByTitle(
        @Query("qInTitle") qInTitle: String,
        @Query("sources") sources: String,
        @Query("from") fromDate: String,
        @Query("to") toDate: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String = RetrofitClient.BASE_API_KEY
    ): NewsResponse
}