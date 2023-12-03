package com.example.task.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys=["user_id", "film_id"],
    foreignKeys=[
        ForeignKey(
            entity=User::class,
            parentColumns=["id"],
            childColumns=["user_id"]
        ),
        ForeignKey(
            entity=Film::class,
            parentColumns=["id"],
            childColumns=["film_id"]
        )
    ])
data class UserFilmCrossRef(
    @ColumnInfo(name="user_id")
    val userId : Int,
    @ColumnInfo(name="film_id")
    val filmId : Int
)
