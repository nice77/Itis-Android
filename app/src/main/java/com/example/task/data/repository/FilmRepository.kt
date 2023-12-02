package com.example.task.data.repository

import android.content.Context
import com.example.task.App
import com.example.task.data.AppDatabase
import com.example.task.data.dao.FilmDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmRepository(ctx : Context) {
    private val db : AppDatabase = App.getDatabase()!!
    private val filmDao : FilmDao = db.getFilmDao()

    suspend fun getAll() {
        withContext(Dispatchers.IO) {
            filmDao.getAll()
        }
    }
}