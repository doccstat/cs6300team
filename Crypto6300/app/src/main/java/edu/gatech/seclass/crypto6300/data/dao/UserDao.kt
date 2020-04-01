package edu.gatech.seclass.crypto6300.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM Users WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): LiveData<User>

    @Query("SELECT * FROM Users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE category is NOT NULL")
    fun getPlayers(): LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE id = :userId LIMIT 1")
    fun getById(userId: String): LiveData<User>

    @Query("SELECT * FROM Users WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByLoginInfo(username: String, password: String): LiveData<User>

    @Query("UPDATE Users SET wins = wins + 1 WHERE Users.id = :userId")
    fun updateUserWin(userId: String)

    @Query("UPDATE Users SET losses = losses + 1 WHERE Users.id = :userId")
    fun updateUserLoss(userId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Query("DELETE FROM Users where category is NOT NULL")
    fun deleteAllPlayers()
}