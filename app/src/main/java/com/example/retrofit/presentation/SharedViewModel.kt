package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.News
import com.example.retrofit.domain.NewsInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    val news: LiveData<List<News>> get() = _news
    private val _news = MutableLiveData<List<News>>()

    fun loadNetworkNews() {
        viewModelScope.launch {
            _news.value = newsInteractor.getNetworkNews()
        }
    }

    fun deleteNews(title: String): List<News>? {
        viewModelScope.launch {
            newsInteractor.deleteDataNews(title)
        }
        return _news.value
    }

    fun loadDatabaseNews() {
        viewModelScope.launch {
            _news.value = newsInteractor.getDataNews()
        }
    }

    fun insertNews(news: News) {
        viewModelScope.launch {
            newsInteractor.insertDataNews(news)
        }
    }

    fun onNewsItemClicked(position: Int) {
        val item = _news.value?.get(position) ?: return
        val list = _news.value?.toMutableList() ?: return

        list[position] = item.copy(isIconClicked = !item.isIconClicked)

        _news.value = list
    }
}