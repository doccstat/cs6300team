package edu.gatech.seclass.crypto6300.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
        tableName = "Users",
        indices = [Index(value = ["username"], unique = true)]
    )
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
) {
    constructor(
            firstName: String = "",
            lastName: String = "",
            username: String = "",
            password: String = "",
            category: Int? = null,
            wins: Int = 0,
            losses: Int = 0
    ) : this(null, firstName, lastName, username, password, category, wins, losses)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (category != other.category) return false
        if (wins != other.wins) return false
        if (losses != other.losses) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + (category ?: 0)
        result = 31 * result + wins
        result = 31 * result + losses
        return result
    }
}

fun User.isAdmin(): Boolean {
    return this.category == null
}

fun User.isPlayer(): Boolean {
    return this.category != null
}