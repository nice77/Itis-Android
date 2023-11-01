package com.example.task.data

import androidx.annotation.DrawableRes

data class NewsData(
    val id : Int,
    val header : String,
    val body : String,
    @DrawableRes val image : Int,
    val favorite : Boolean = false
) {
    override fun toString(): String {
        return "NewsData(id=$id)"
    }
}
