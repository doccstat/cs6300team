package edu.gatech.seclass.crypto6300.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "Cryptogram"
)
data class Cryptogram(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "solution")
        val solution: String,
        @ColumnInfo(name = "difficulty")
        val difficulty: Int,

        @Embedded
        val maxAttempts: Attempts
)

data class Attempts(
        val easy: Int,
        val normal: Int,
        val hard: Int
)