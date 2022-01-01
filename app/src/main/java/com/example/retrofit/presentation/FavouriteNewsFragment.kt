package com.example.retrofit.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.retrofit.R
import com.example.retrofit.domain.News
import com.example.retrofit.presentation.recycler.NewsAdapter
import com.example.retrofit.presentation.recycler.OnNewsClickListener
import com.example.retrofit.utils.prefs.SharedPreferenceImpl
import kotlinx.android.synthetic.main.favourite_news_layout.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavouriteNewsFragment : Fragment(R.layout.favourite_news_layout) {

    private val viewModel: SharedViewModel by sharedViewModel()
    private val adapter by lazy { NewsAdapter(newsClickListener) }

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {
        override fun onIconClickListener(position: Int) {
            viewModel.onNewsItemClicked(position)
            val prefs = SharedPreferenceImpl(requireContext())
            viewModel.news.value?.get(position)?.isIconClicked = prefs.getBoolean(position.toString())
            prefs.saveBoolean(position.toString(), !prefs.getBoolean(position.toString()))
            val news = News(
                title = viewModel.news.value?.get(position)!!.title,
                text = viewModel.news.value?.get(position)!!.text,
                date = viewModel.news.value?.get(position)!!.date,
                author = viewModel.news.value?.get(position)!!.author,
                iconUrl = viewModel.news.value?.get(position)!!.iconUrl,
                articleUrl = viewModel.news.value?.get(position)!!.articleUrl,
                isIconClicked = viewModel.news.value?.get(position)!!.isIconClicked
            )
            if (prefs.getBoolean(position.toString())) {
                viewModel.insertNews(news)
            } else {
                viewModel.deleteNews(news)
            }
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
        viewModel.loadDatabaseNews()
        networkNews.setOnClickListener {
            findNavController().navigate(R.id.toNetworkNews)
        }
    }

    private fun initRecycler() {
        favouriteRecycler.adapter = adapter
    }

    private fun initObserves() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }
}