package com.example.task.items

import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.NewsData
import com.example.task.databinding.ItemNewsBinding

class NewsListItem(
    private val binding : ItemNewsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(newsItem : NewsData) {
        binding.headerTv.text = newsItem.header
        binding.bodyTv.text = newsItem.header
    }

}