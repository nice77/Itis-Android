package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemButtonBinding
import com.example.task.databinding.ItemDateBinding
import com.example.task.databinding.ItemNewsBinding
import com.example.task.items.ButtonListItem
import com.example.task.items.DateListItem
import com.example.task.items.NewsListItem
import com.example.task.repository.NewsRepository
import com.example.task.utils.ItemTypes

class NewsListAdapter(
    private val listSize : Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemTypes.BUTTON_ITEM.id -> ButtonListItem(binding = ItemButtonBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
            ItemTypes.DATE_ITEM.id -> DateListItem(binding = ItemDateBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
            else -> NewsListItem(binding = ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context)
            ))
        }
    }

    override fun getItemCount(): Int {
        return this.listSize
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ButtonListItem -> holder.bind()
            is DateListItem -> holder.bind()
            is NewsListItem -> holder.bind(NewsRepository.getNewsList()[position])
        }
    }
}