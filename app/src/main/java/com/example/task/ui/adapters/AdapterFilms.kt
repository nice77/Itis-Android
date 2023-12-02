package com.example.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemFilmBinding

class AdapterFilms : RecyclerView.Adapter<AdapterFilms.FilmItem>() {

    inner class FilmItem(binding : ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItem {
        return FilmItem(
            binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FilmItem, position: Int) {
        TODO("Not yet implemented")
    }

}