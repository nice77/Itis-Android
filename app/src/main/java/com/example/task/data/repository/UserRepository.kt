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

    suspend fun addUser(user : User) {
        withContext(Dispatchers.IO) {
            userDao.add(user)
        }
    }

    suspend fun updateUser(user : User) {
        withContext(Dispatchers.IO) {
            userDao.update(user)
        }
    }
}
