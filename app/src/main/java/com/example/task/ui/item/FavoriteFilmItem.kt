package com.example.task.ui.item

import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.entities.Film
import com.example.task.databinding.ItemFavoriteFilmBinding

class FavoriteFilmItem(
    private val binding : ItemFavoriteFilmBinding,
    private val onItemClicked : (Film) -> Unit,
    private val onFavoriteButtonClicked : (Film) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var filmData : Film?= null
    fun bind(film : Film) {
        filmData = film
        binding.let {
            it.titleTv.text = film.name
            it.yearTv.text = film.year.toString()
            it.root.setOnClickListener { view ->
                onItemClicked(filmData!!)
            }
            it.removeFromFavoritesBtn.setOnClickListener {
                onFavoriteButtonClicked(film)
            }
        }
    }
}
