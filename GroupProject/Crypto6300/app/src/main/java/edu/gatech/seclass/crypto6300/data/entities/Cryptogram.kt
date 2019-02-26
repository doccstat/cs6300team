package edu.gatech.seclass.crypto6300.data.entities

import androidx.room.ColumnInfo
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

        @ColumnInfo(name = "max_attempts")
        val maxAttempts: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cryptogram

        if (name != other.name) return false
        if (solution != other.solution) return false
        if (difficulty != other.difficulty) return false
        if (!maxAttempts.contentEquals(other.maxAttempts)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + solution.hashCode()
        result = 31 * result + difficulty
        result = 31 * result + maxAttempts.contentHashCode()
        return result
    }
}