package com.example.task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.task.data.entities.UserFilmCrossRef

@Dao
interface UserFilmCrossRefDao {
    @Insert
    fun add(userFilmCrossRef: UserFilmCrossRef)

    @Delete
    fun delete(userFilmCrossRef: UserFilmCrossRef)

    @Query("DELETE FROM UserFilmCrossRef WHERE film_id = :id")
    fun delete(id : Int)
}