package com.example.task.data.repository

import com.example.task.App
import com.example.task.data.AppDatabase
import com.example.task.data.entities.UserFilmCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserFilmCrossRefRepository {
    private val db : AppDatabase = App.getDatabase()!!
    private val userFilmCrossRefDao = db.getUserFilmCrossRefDao()

    suspend fun add(userFilmCrossRef: UserFilmCrossRef) {
        return withContext(Dispatchers.IO) {
            userFilmCrossRefDao.add(userFilmCrossRef)
        }
    }

    suspend fun delete(userFilmCrossRef: UserFilmCrossRef) {
        return withContext(Dispatchers.IO) {
            userFilmCrossRefDao.delete(userFilmCrossRef)
        }
    }

    suspend fun delete(id : Int) {
        return withContext(Dispatchers.IO) {
            userFilmCrossRefDao.delete(id)
        }
    }
}