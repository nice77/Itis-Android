package com.example.task.data

data class NewsData(
    val id : Int,
    val header : String,
    val body : String,
    val favorite : Boolean = false
)
