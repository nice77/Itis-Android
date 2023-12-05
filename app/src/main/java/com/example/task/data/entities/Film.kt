package com.example.task.data.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="films")
data class Film(
    @PrimaryKey(autoGenerate=true)
    val id : Int = 0,
    val name : String,
    val year : Int,
    val description : String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Film

        if (name != other.name) return false
        if (year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + year
        result = 31 * result + description.hashCode()
        return result
    }
}
