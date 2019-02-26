package edu.gatech.seclass.crypto6300.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "firstname")
        val firstName: String,
        @ColumnInfo(name = "lastname")
        val lastName: String,

        @ColumnInfo(name = "username")
        val username: String,
        @ColumnInfo(name = "password")
        val password: String,

        @ColumnInfo(name = "category")
        val category: Int? = null,

        @ColumnInfo(name = "wins")
        val wins: Int,
        @ColumnInfo(name = "losses")
        val losses: Int
)

fun User.isAdmin(): Boolean {
        return this.category == null
}

fun User.isPlayer(): Boolean {
        return this.category != null
}