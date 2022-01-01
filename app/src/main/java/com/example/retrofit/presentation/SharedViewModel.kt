package com.example.retrofit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.News
import com.example.retrofit.domain.NewsInteractor
import com.example.retrofit.utils.prefs.SharedPreferenceManager
import kotlinx.coroutines.launch

class SharedViewModel(
    private val interactor: NewsInteractor,
    private val sharedPreferenceManager: SharedPreferenceManager
) : ViewModel() {

    val news: LiveData<List<News>> get() = _news
    private val _news = MutableLiveData<List<News>>()

    fun loadNetworkNews() {
        viewModelScope.launch {
            _news.value = interactor.getNetworkNews()
        }
    }

    fun deleteNetworkNews() {
        viewModelScope.launch {
            _news.value = interactor.deleteNetworkNews()
        }
    }

    fun deleteNews(news: News) {
        viewModelScope.launch {
            interactor.deleteDataNews(news)
        }
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