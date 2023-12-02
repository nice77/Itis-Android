package com.example.task.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemFavoriteBinding

class AdapterFavorites : RecyclerView.Adapter<AdapterFavorites.FavoriteItem>() {

    inner class FavoriteItem(binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItem {
        return FavoriteItem(
            binding = ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteItem, position: Int) {
        TODO("Not yet implemented")
    }

}