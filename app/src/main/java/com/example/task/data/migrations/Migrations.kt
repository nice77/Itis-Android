package com.example.task.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val query = "CREATE TABLE rates (" +
                    "film_id INTEGER NOT NULL," +
                    "user_id INTEGER NOT NULL," +
                    "rate INTEGER NOT NULL," +
                    "PRIMARY KEY(film_id, user_id)," +
                    "FOREIGN KEY (film_id) REFERENCES films(id)," +
                    "FOREIGN KEY (user_id) REFERENCES users(id)" +
                    ")"
            database.execSQL(query)
        }
    }

}