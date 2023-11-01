package com.example.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams
import com.example.task.adapter.diffutils.DiffCallBack
import com.example.task.data.NewsData
import com.example.task.databinding.ItemButtonBinding
import com.example.task.databinding.ItemDateBinding
import com.example.task.databinding.ItemNewsBinding
import com.example.task.items.ButtonListItem
import com.example.task.items.DateListItem
import com.example.task.items.NewsListItem
import com.example.task.repository.NewsFeedRepository
import com.example.task.utils.ItemTypes

class NewsListAdapter(
    private val onItemClicked: (NewsData) -> Unit,
    private val onLikeButtonClicked : (Int, NewsData) -> Unit,
    private val onButtonClicked: () -> Unit,
    private val onItemSwiped : (Int) -> Unit,
    private val onItemDeleted : () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val currentItem = when (viewType) {
            ItemTypes.BUTTON_ITEM.id -> ButtonListItem(
                binding = ItemButtonBinding.inflate(
                    LayoutInflater.from(parent.context)
                ),
                onButtonClicked = onButtonClicked
            )

            ItemTypes.DATE_ITEM.id -> DateListItem(
                binding = ItemDateBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
            )

            else -> NewsListItem(
                binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClicked = onItemClicked,
                onLikeButtonClicked = onLikeButtonClicked,
                onItemSwiped = onItemSwiped,
                onItemDeleted = onItemDeleted
            )
        }
        return currentItem
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ItemTypes.BUTTON_ITEM.id
            in NewsFeedRepository.getDatesList() -> ItemTypes.DATE_ITEM.id
            else -> ItemTypes.NEWS_ITEM.id
        }
    }

    override fun getItemCount(): Int {
        return NewsFeedRepository.getSize()
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            (payloads.first() as? Boolean)?.let {
                (holder as? NewsListItem)?.changeLikeButton(it)
            }
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ButtonListItem -> {}
            is DateListItem -> holder.bind()
            is NewsListItem -> holder.bind(NewsFeedRepository.getNewsList()[position] as NewsData)
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder !is NewsListItem) {
            holder.itemView.updateLayoutParams {
                (this as? LayoutParams)?.isFullSpan = true
            }
        }
    }

    fun updateItems(oldList : List<Any?>, newList: List<Any?>) {
        val diff = DiffCallBack(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        diffResult.dispatchUpdatesTo(this)
    }
}