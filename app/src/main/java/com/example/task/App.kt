package com.example.task

import android.app.Application
import androidx.room.Room
import com.example.task.data.AppDatabase


class App : Application() {
    override fun onCreate() {
        println(43)
        super.onCreate()
        println(44)
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    companion object {
        private val DATABASE_NAME = "app.db"
        private var instance: App ?= null
        private var database: AppDatabase ?= null
        fun getInstance(): App? = instance
        fun getDatabase(): AppDatabase? = database
    }
}