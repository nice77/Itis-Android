package com.example.task.data.repository

import android.content.Context
import com.example.task.App
import com.example.task.data.dao.UserDao
import com.example.task.data.entities.Film
import com.example.task.data.entities.User
import com.example.task.data.entities.UserWithFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private val db = App.getDatabase()!!
    private val userDao : UserDao = db.getUserDao()

    suspend fun addUser(user : User) : Boolean {
        return withContext(Dispatchers.IO) {
            if (userDao.checkIfNumberExists(user.phone)) {
                return@withContext false
            }
            userDao.add(user)
            return@withContext true
        }
    }

    suspend fun updateUser(user : User) {
        withContext(Dispatchers.IO) {
            userDao.update(user)
        }
    }

    suspend fun checkUserCredentials(email : String, password : String) : Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.checkUserCredentials(email, password)
        }
    }

    suspend fun getUserWithFilms(id : Int) : List<Film> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getAllUsersWithFilms(id)
        }.films
    }

    suspend fun getUserByCredentials(email : String, password: String) : User {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUserByCredentials(email, password)
        }
    }

    suspend fun getUserById(id : Int) : User {
        return withContext(Dispatchers.IO) {
            println(userDao.getUserById(id))
            return@withContext userDao.getUserById(id)
        }
    }
}
