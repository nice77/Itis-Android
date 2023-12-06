package com.example.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.entities.Film
import com.example.task.data.entities.FilmUserRef
import com.example.task.databinding.ItemFilmBinding
import com.example.task.ui.adapters.diffutil.DiffCallBackFilms
import com.example.task.ui.item.FilmItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class AdapterFilms(
    private var filmsList : List<FilmUserRef>,
    private val onItemClicked : (Film) -> Unit,
    private val onButtonClicked : (FilmUserRef) -> Unit
) : RecyclerView.Adapter<FilmItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItem {
        return FilmItem(
            binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onItemClicked = onItemClicked,
            onButtonClicked = onButtonClicked
        )
    }

    override fun getItemCount(): Int {
        return filmsList.size
    }

    override fun onBindViewHolder(holder: FilmItem, position: Int) {
        holder.bind(filmsList[position])
    }

    fun getList() : List<FilmUserRef> = filmsList

    override fun onBindViewHolder(holder: FilmItem, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            println("Payloads: $payloads; position: $position; list: $filmsList")
            holder.bind((payloads as List<FilmUserRef>)[0])
        }
        else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    fun updateItems(newList : List<FilmUserRef>) {
        val diff = DiffCallBackFilms(filmsList, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        filmsList = newList
        println("New filmsList: $filmsList")
        diffResult.dispatchUpdatesTo(this)
    }
}