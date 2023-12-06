package com.example.task.data.repository

import com.example.task.App
import com.example.task.data.AppDatabase
import com.example.task.data.dao.FilmDao
import com.example.task.data.dao.RateDao
import com.example.task.data.entities.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RateRepository {
    private val db : AppDatabase = App.getDatabase()!!
    private val rateDao : RateDao = db.getRateDao()

    suspend fun insert(rate : Rate) {
        withContext(Dispatchers.IO) {
            rateDao.add(rate)
        }
    }

    suspend fun get(filmId : Int, userId : Int) : Rate? {
        return withContext(Dispatchers.IO) {
            rateDao.get(filmId, userId)
        }
    }

    suspend fun getAvg(filmId : Int) : Float {
        return withContext(Dispatchers.IO) {
            rateDao.getAvg(filmId)
        }
    }

    suspend fun deleteAllMatches(filmId : Int) {
        withContext(Dispatchers.IO) {
            rateDao.deleteAllMatches(filmId)
        }
    }
}