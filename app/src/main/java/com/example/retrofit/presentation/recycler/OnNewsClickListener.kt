package com.example.retrofit.presentation.recycler

import com.example.retrofit.domain.News

interface OnNewsClickListener {
    fun onIconClickListener(position: Int)
    fun onItemClickListener(news: News)
}