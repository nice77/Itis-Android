package com.example.task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task.data.dao.FilmDao
import com.example.task.data.dao.UserDao
import com.example.task.data.entities.Film
import com.example.task.data.entities.User
import com.example.task.data.entities.UserFilmCrossRef

@Database(entities=[User::class, Film::class, UserFilmCrossRef::class], version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getFilmDao() : FilmDao
}