package com.example.retrofit.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofit.DaggerApplication
import com.example.retrofit.R
import com.example.retrofit.domain.News
import com.example.retrofit.presentation.recycler.NewsAdapter
import com.example.retrofit.presentation.recycler.OnNewsClickListener
import com.example.retrofit.utils.prefs.SharedPreferenceManager
import kotlinx.android.synthetic.main.news_fragment_layout.*
import javax.inject.Inject

class NewsFragment : Fragment(R.layout.news_fragment_layout) {

    @Inject
    lateinit var viewModel: SharedViewModel

    @Inject
    lateinit var prefs: SharedPreferenceManager

    private val adapter by lazy { NewsAdapter(newsClickListener) }

    init {
        DaggerApplication.appComponent?.inject(this)
    }

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {
        override fun onIconClickListener(position: Int) {
            val key = viewModel.news.value?.get(position)?.title
            viewModel.news.value?.get(position)?.isIconClicked =
                prefs.getBoolean(key.orEmpty())
            prefs.saveBoolean(key.orEmpty(), !prefs.getBoolean(key.orEmpty()))

            if (prefs.getBoolean(key.orEmpty())) {
                viewModel.insertNews(viewModel.news.value!![position])
            } else {
                viewModel.deleteNews(viewModel.news.value?.get(position)!!.title)

            }

            viewModel.onNewsItemClicked(position)
        }

        override fun onItemClickListener(news: News) {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(news.articleUrl)
            startActivity(openURL)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObserves()
        viewModel.loadNetworkNews()
        favouriteNews.setOnClickListener {
            findNavController().navigate(R.id.toFavouriteNews)
        }
    }

    private fun initRecycler() {
        recycler.adapter = adapter
    }

    private fun initObserves() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }
}