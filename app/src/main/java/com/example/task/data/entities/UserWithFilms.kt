package com.example.task.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithFilms(
    @Embedded val user : User,
    @Relation(
        parentColumn="id",
        entityColumn="id",
        entity=Film::class,
        associateBy=
            Junction(
                UserFilmCrossRef::class,
                parentColumn="user_id",
                entityColumn="film_id"
            )
    )
    val films : List<Film>
)
