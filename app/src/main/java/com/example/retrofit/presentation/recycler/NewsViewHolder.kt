package com.example.retrofit.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.domain.News
import kotlinx.android.synthetic.main.news_layout.view.*

class NewsViewHolder(
    itemView: View,
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun fromParent(parent: ViewGroup, itemClickListener: OnNewsClickListener) =
            NewsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.news_layout, parent, false),
                itemClickListener
            )
    }

    private val title: TextView by lazy { itemView.title }
    private val author: TextView by lazy { itemView.author }
    private val date: TextView by lazy { itemView.date }
    private val text: TextView by lazy { itemView.text }
    private val iconCheckBox: CheckBox by lazy { itemView.save }
    private val icon by lazy { itemView.icon }

    fun bindView(item: News) {
        title.text = item.title
        author.text = item.author
        date.text = item.date
        text.text = item.text

        loadImageByUrl(item.iconUrl)

        iconCheckBox.isChecked = item.isIconClicked

        text.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }

        title.setOnClickListener {
            itemClickListener.onItemClickListener(item)
        }

        iconCheckBox.setOnClickListener {
            itemClickListener.onIconClickListener(adapterPosition)
        }
    }

    private fun loadImageByUrl(url: String) {
        Glide.with(icon.context)
            .load(url)
            .into(icon)
    }
}