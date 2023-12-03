package com.example.task.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="films")
data class Film(
    @PrimaryKey(autoGenerate=true)
    val id : Int = 0,
    val name : String,
    val year : Int,
    val description : String
)
