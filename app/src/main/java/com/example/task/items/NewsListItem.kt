package com.example.task.items

import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.data.NewsData
import com.example.task.databinding.ItemNewsBinding

class NewsListItem(
    private val binding : ItemNewsBinding,
    private val onItemClicked: (NewsData) -> Unit,
    private val onLikeButtonClicked : (Int, NewsData) -> Unit,
    private val onItemSwiped : (Int) -> Unit,
    private val onItemDeleted : () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var newsDataItem : NewsData ?= null

    fun bind(newsItem : NewsData) {
        this.newsDataItem = newsItem

        binding.imageImg.setImageResource(newsItem.image)
        binding.headerTv.text = newsItem.header
        binding.favoriteImg.setImageResource(
            if (newsItem.favorite)
                R.drawable.baseline_thumb_up_alt_24
            else R.drawable.baseline_thumb_up_off_alt_24
        )

        binding.favoriteImg.setOnClickListener {
            this.newsDataItem?.let {
                val data = it.copy(favorite = !it.favorite)
                onLikeButtonClicked(adapterPosition, data)
            }
        }
        binding.root.setOnClickListener {
            onItemClicked(newsItem)
        }
        binding.trashImg.setOnClickListener {
            this.remove()
        }
    }

    fun changeLikeButton(result : Boolean) {
        binding.favoriteImg.setImageResource(if (result)
            R.drawable.baseline_thumb_up_alt_24
            else R.drawable.baseline_thumb_up_off_alt_24
        )
    }

    fun remove() {
        onItemSwiped(newsDataItem!!.id)
        onItemDeleted()
    }

    fun canPopUp() {
        if (binding.trashImg.visibility == android.view.View.GONE) {
            binding.trashImg.visibility = android.view.View.VISIBLE
        }
        else {
            binding.trashImg.visibility = android.view.View.GONE
        }
    }
}