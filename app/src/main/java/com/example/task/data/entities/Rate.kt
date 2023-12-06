package com.example.task.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName="rates",
    primaryKeys=["film_id", "user_id"],
    foreignKeys=[
        ForeignKey(
            entity=Film::class,
            parentColumns=["id"],
            childColumns=["film_id"]
        ),
        ForeignKey(
            entity=User::class,
            parentColumns=["id"],
            childColumns=["user_id"]
        )
    ]
)
data class Rate(
    @ColumnInfo(name="film_id")
    val filmId : Int,
    @ColumnInfo(name="user_id")
    val userId : Int,
    val rate : Int
)