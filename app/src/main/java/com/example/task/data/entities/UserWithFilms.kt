package com.example.task.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithFilms(
    @Embedded val user : User,
    @Relation(
        parentColumn="id",
        entityColumn="filmId",
        associateBy=
            Junction(UserFilmCrossRef::class)
    )
    val films : List<Film>
)
