package com.example.retrofit.presentation.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.domain.News
import com.example.retrofit.utils.prefs.SharedPreferenceImpl

class NewsAdapter(
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsViewHolder>() {

    private var items: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.fromParent(parent, itemClickListener)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val prefs = SharedPreferenceImpl(holder.itemView.context)
        val item = items[position]
        val list = items.toMutableList()
        list[position] = item.copy(isIconClicked = prefs.getBoolean(items[position].title))
        items = list

        holder.bindView(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(data: List<News>) {
        items = data
        notifyDataSetChanged()
    }
}