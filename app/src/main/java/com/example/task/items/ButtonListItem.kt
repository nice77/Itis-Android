package com.example.task.items

import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemButtonBinding

class ButtonListItem(
    private val binding : ItemButtonBinding,
    private val onButtonClicked : () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.openBottomSheetBtn.setOnClickListener {
            onButtonClicked()
        }
    }

}