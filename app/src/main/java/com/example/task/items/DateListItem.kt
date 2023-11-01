package com.example.task.items

import androidx.recyclerview.widget.RecyclerView
import com.example.task.databinding.ItemDateBinding
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DateListItem (
    private val binding : ItemDateBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        val date = Date()
        val format = SimpleDateFormat("HH:mm:ss")
        binding.dateTv.text = "${format.format(date)}"
    }

}