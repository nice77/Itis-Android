package com.example.task.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys=["userId", "filmId"],
    foreignKeys=[
        ForeignKey(
            entity=User::class,
            parentColumns=["id"],
            childColumns=["userId"]
        ),
        ForeignKey(
            entity=Film::class,
            parentColumns=["id"],
            childColumns=["filmId"]
        )
    ])
data class UserFilmCrossRef(
//    @ColumnInfo(name="user_id")
    val userId : Int,
//    @ColumnInfo(name="film_id")
    val filmId : Int
)
