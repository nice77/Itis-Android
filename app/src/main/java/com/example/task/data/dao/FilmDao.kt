package com.example.task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task.data.entities.Film
import com.example.task.data.entities.FilmUserRef

@Dao
interface FilmDao {
    @Insert(onConflict=OnConflictStrategy.ABORT)
    fun add(film : Film)

    @Query("SELECT * FROM films ORDER BY year")
    fun getAll() : List<Film>

    @Update
    fun update(film : Film)

    @Delete
    fun delete(film : Film)

    @Query("SELECT * FROM films ORDER BY id DESC LIMIT 1")
    fun getLast() : Film

    @Query("SELECT films.*, user_id FROM films LEFT JOIN UserFilmCrossRef ON film_id = id WHERE user_id = :userId OR user_id IS NULL ORDER BY year DESC")
    fun getAllRef(userId : Int) : List<FilmUserRef>

    @Query("SELECT films.*, user_id FROM films LEFT JOIN UserFilmCrossRef ON film_id = id WHERE user_id = :userId OR user_id IS NULL ORDER BY year")
    fun getAllRefAsc(userId : Int) : List<FilmUserRef>

    @Query("SELECT * FROM films JOIN UserFilmCrossRef ON id = film_id WHERE user_id = :id")
    fun getAllUserFilms(id : Int) : List<Film>

    @Query("SELECT films.*, UserFilmCrossRef.user_id FROM films LEFT JOIN rates ON id = rates.film_id LEFT JOIN UserFilmCrossRef ON UserFilmCrossRef.film_id = id  WHERE UserFilmCrossRef.user_id = :userId OR UserFilmCrossRef.user_id IS NULL ORDER BY rate")
    fun getAllByRateAsc(userId : Int) : List<FilmUserRef>

    @Query("SELECT films.*, UserFilmCrossRef.user_id FROM films LEFT JOIN rates ON id = rates.film_id LEFT JOIN UserFilmCrossRef ON UserFilmCrossRef.film_id = id WHERE UserFilmCrossRef.user_id = :userId OR UserFilmCrossRef.user_id IS NULL ORDER BY rate DESC")
    fun getAllByRateDesc(userId : Int) : List<FilmUserRef>

}