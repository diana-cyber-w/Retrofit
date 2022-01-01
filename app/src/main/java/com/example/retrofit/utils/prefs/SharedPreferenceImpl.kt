package com.example.retrofit.utils.prefs

import android.content.Context

class SharedPreferenceImpl(private val context: Context) : SharedPreferenceManager {

    companion object {
        private const val SHARED_PREFS_NAME = "SHARED_PREFS_NAME"

        private const val DEFAULT_BOOLEAN_VALUE = false
    }

    private val prefs by lazy {
        context.getSharedPreferences(
            SHARED_PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun saveBoolean(key: String, value: Boolean) {
        prefs.edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, DEFAULT_BOOLEAN_VALUE)
    }
}