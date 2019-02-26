package edu.gatech.seclass.crypto6300.data.dao

import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM Users WHERE category is NOT NULL")
    fun getPlayers(): List<User>

    @Query("SELECT * FROM Users WHERE id = :userId LIMIT 1")
    fun getById(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Query("DELETE FROM Users where category is NOT NULL")
    fun DeleteAllPlayers()
}