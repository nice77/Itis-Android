package com.example.task.ui.item

import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.entities.Film
import com.example.task.data.entities.FilmUserRef
import com.example.task.databinding.ItemFilmBinding
import com.example.task.domain.CurrentUser

class FilmItem(
    private val binding : ItemFilmBinding,
    private val onItemClicked : (Film) -> Unit,
    private val onButtonClicked : (FilmUserRef) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var filmData : FilmUserRef ?= null
    fun bind(filmUserRef: FilmUserRef) {
        println("Bind() method: $filmUserRef")
        filmData = filmUserRef
        println("filmData: $filmData")
        binding.let {
            it.titleTv.text = filmUserRef.film.name
            it.textTv.text = filmUserRef.film.description
            println("filmData.added: ${filmData!!.added}")
            if (filmData!!.added == CurrentUser.get()) {
                it.addToFavoritesBtn.text = "Remove from favorites"
            }
            else {
                it.addToFavoritesBtn.text = "Add to favorites"
            }

            it.root.setOnClickListener { view ->
                onItemClicked(filmData!!.film)
            }
            it.addToFavoritesBtn.setOnClickListener { view ->
                onButtonClicked(filmData!!)
                if (filmData!!.added == CurrentUser.get()) {
                    it.addToFavoritesBtn.text = "Add to favorites"
                }
                else {
                    it.addToFavoritesBtn.text = "Remove from favorites"
                }
            }
        }
    }

    fun getFilmUserRef() = filmData
}