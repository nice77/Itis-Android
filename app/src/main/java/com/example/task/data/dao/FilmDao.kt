package com.example.task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task.data.entities.Film

@Dao
interface FilmDao {
    @Insert(onConflict=OnConflictStrategy.ABORT)
    fun add(film : Film)

    @Query("SELECT * FROM films")
    fun getAll() : List<Film>

    @Update
    fun update(film : Film)

    @Delete
    fun delete(film : Film)
}