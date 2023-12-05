package com.example.task.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class FilmUserRef(
    @Embedded val film: Film,
    @ColumnInfo(name="user_id", defaultValue="-1")
    var added: Int
)
