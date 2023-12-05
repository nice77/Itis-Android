package com.example.task.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task.data.entities.User
import com.example.task.data.entities.UserWithFilms

@Dao
interface UserDao {
    @Insert(onConflict=OnConflictStrategy.ABORT)
    fun add(user : User)

    @Query("SELECT * FROM users")
    fun getAll() : List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id : Int) : User

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun getUserByCredentials(email : String, password : String) : User

    @Update
    fun update(user : User)

    @Delete
    fun delete(user : User)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getAllUsersWithFilms(id : Int) : UserWithFilms

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE phone = :phone LIMIT 1)")
    fun checkIfNumberExists(phone : String) : Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE email = :email AND password = :password LIMIT 1)")
    fun checkUserCredentials(email : String, password : String) : Boolean
}