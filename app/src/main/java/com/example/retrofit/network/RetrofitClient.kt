package com.example.retrofit.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_API_KEY = "fa04db0f322849559d0e4310e1d3d5a5"
    private const val BASE_URL = "https://newsapi.org/v2/"

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getClient(url: String = BASE_URL) = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getNewsApi(): NewsApi = getClient().create(NewsApi::class.java)
}