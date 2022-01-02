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
import kotlinx.android.synthetic.main.news_fragment_layout.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class NewsFragment : Fragment(R.layout.news_fragment_layout) {

    private val viewModel: SharedViewModel by sharedViewModel()
    private val adapter by lazy { NewsAdapter(newsClickListener) }

    private val newsClickListener: OnNewsClickListener = object : OnNewsClickListener {
        override fun onIconClickListener(position: Int) {
            val prefs = SharedPreferenceImpl(requireContext())
            viewModel.news.value?.get(position)?.isIconClicked =
                prefs.getBoolean(position.toString())
            prefs.saveBoolean(position.toString(), !prefs.getBoolean(position.toString()))

            if (prefs.getBoolean(position.toString())) {
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