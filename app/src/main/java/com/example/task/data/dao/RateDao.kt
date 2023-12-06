package com.example.task.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task.data.entities.Rate

@Dao
interface RateDao {

    @Insert
    fun add(rate : Rate)

    @Query("SELECT * FROM rates WHERE film_id = :filmId AND user_id = :userId")
    fun get(filmId : Int, userId : Int) : Rate?

    @Query("SELECT AVG(rate) FROM rates WHERE film_id = :filmId")
    fun getAvg(filmId : Int) : Float

    @Query("DELETE FROM rates WHERE film_id = :filmId")
    fun deleteAllMatches(filmId : Int)
}