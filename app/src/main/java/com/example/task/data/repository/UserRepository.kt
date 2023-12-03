package com.example.task.data.repository

import android.content.Context
import com.example.task.App
import com.example.task.data.dao.UserDao
import com.example.task.data.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(ctx : Context) {
    private val db = App.getDatabase()!!
    private val userDao : UserDao = db.getUserDao()

    suspend fun addUser(user : User) : Boolean {
        return withContext(Dispatchers.IO) {
            println("Number exists: " + userDao.checkIfNumberExists(user.phone))
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
}
