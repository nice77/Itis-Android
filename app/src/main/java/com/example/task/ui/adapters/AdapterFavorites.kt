package com.example.task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.entities.Film
import com.example.task.databinding.ItemFavoriteFilmBinding
import com.example.task.ui.adapters.diffutil.DiffCallBackFavorites
import com.example.task.ui.item.FavoriteFilmItem

class AdapterFavorites(
    private var favoritesList : List<Film>,
    private val onItemClicked : (Film) -> Unit,
    private val onFavoriteButtonClicked : (Film) -> Unit
) : RecyclerView.Adapter<FavoriteFilmItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteFilmItem {
        return FavoriteFilmItem (
            binding = ItemFavoriteFilmBinding.inflate(
                LayoutInflater.from(parent.context)
            ),
            onItemClicked = onItemClicked,
            onFavoriteButtonClicked = onFavoriteButtonClicked
        )
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    override fun onBindViewHolder(holder: FavoriteFilmItem, position: Int) {
        holder.bind(favoritesList[position])
    }

    fun updateItems(newList: List<Film>) {
        val diff = DiffCallBackFavorites(favoritesList, newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        favoritesList = newList
        println("FavoritesList: $favoritesList")
        diffResult.dispatchUpdatesTo(this)
    }
}
