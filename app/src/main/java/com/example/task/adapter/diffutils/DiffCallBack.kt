package com.example.task.adapter.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.task.data.NewsData

class DiffCallBack(
    private val oldList : List<Any?>,
    private val newList : List<Any?>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition] as? NewsData
        val new = newList[newItemPosition] as? NewsData
        return old?.id == new?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition] as? NewsData
        val new = newList[newItemPosition] as? NewsData
        return old?.header == new?.header &&
                old?.body == new?.body
    }
}