package com.example.task.ui.adapters.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.task.data.entities.Film

class DiffCallBackFavorites(
    private val oldList : List<Film>,
    private val newList : List<Film>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm.id == newFilm.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm.year == newFilm.year && oldFilm.name == newFilm.name
    }
}