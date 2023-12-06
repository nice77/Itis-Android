package com.example.task.ui.adapters.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.task.data.entities.FilmUserRef

class DiffCallBackFilms(
    private val oldList : List<FilmUserRef>,
    private val newList : List<FilmUserRef>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].film.id == newList[newItemPosition].film.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
//        println("Calling cts on:\n$oldItem\n$newItem")
        return oldItem.added == newItem.added
    }
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        println("Calling gcp on:\n$oldItem\n$newItem")
        return if (oldItem.added != newItem.added) {
            newItem
        }
        else {
            super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}