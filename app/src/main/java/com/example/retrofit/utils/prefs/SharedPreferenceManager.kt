package com.example.retrofit.utils.prefs

interface SharedPreferenceManager {
    fun saveBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean
}