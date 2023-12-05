package com.example.task.data.repository

import com.example.task.App
import com.example.task.data.AppDatabase
import com.example.task.data.dao.FilmDao
import com.example.task.data.entities.Film
import com.example.task.data.entities.FilmUserRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmRepository() {
    private val db : AppDatabase = App.getDatabase()!!
    private val filmDao : FilmDao = db.getFilmDao()

    suspend fun getAll() : List<Film> {
        return withContext(Dispatchers.IO) {
            filmDao.getAll()
        }
    }

    suspend fun add(film : Film) : Film {
        return withContext(Dispatchers.IO) {
            filmDao.add(film)
            filmDao.getLast()
        }
    }

    suspend fun getAllRef(userId : Int) : List<FilmUserRef> {
        return withContext(Dispatchers.IO) {
            filmDao.getAllRef(userId)
        }
    }

    suspend fun getAllUserFilms(id : Int) : List<Film> {
        return withContext(Dispatchers.IO) {
            filmDao.getAllUserFilms(id)
        }
    }

    suspend fun getAllRefDesc(userId : Int) : List<FilmUserRef> {
        return withContext(Dispatchers.IO) {
            filmDao.getAllRefAsc(userId)
        }
    }

    suspend fun getByRateAsc(userId: Int) : List<FilmUserRef> {
        return withContext(Dispatchers.IO) {
            filmDao.getAllByRateAsc(userId)
        }
    }

    suspend fun getByRateDesc(userId: Int) : List<FilmUserRef> {
        return withContext(Dispatchers.IO) {
            filmDao.getAllByRateDesc(userId)
        }
    }

    suspend fun delete(film : Film) {
        return withContext(Dispatchers.IO) {
            filmDao.delete(film)
        }
    }
}