package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.News
import com.example.retrofit.domain.NewsInteractor
import kotlinx.coroutines.launch

class SharedViewModel(
    private val interactor: NewsInteractor
) : ViewModel() {

    val news: LiveData<List<News>> get() = _news
    private val _news = MutableLiveData<List<News>>()

    fun loadNetworkNews() {
        viewModelScope.launch {
            _news.value = interactor.getNetworkNews()
        }
    }

    fun deleteNews(title: String): List<News>? {
        viewModelScope.launch {
            interactor.deleteDataNews(title)
        }
        return _news.value
    }

    fun loadDatabaseNews() {
        viewModelScope.launch {
            _news.value = interactor.getDataNews()
        }
    }

    fun insertNews(news: News) {
        viewModelScope.launch {
            interactor.insertDataNews(news)
        }
    }

    fun onNewsItemClicked(position: Int) {
        val item = _news.value?.get(position) ?: return
        val list = _news.value?.toMutableList() ?: return

        list[position] = item.copy(isIconClicked = !item.isIconClicked)

        _news.value = list
    }
}