package com.example.task

import android.app.Application
import androidx.room.Room
import com.example.task.data.AppDatabase
import com.example.task.data.migrations.Migrations


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, DATABASE_NAME)
            .addMigrations(Migrations.MIGRATION_1_2)
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