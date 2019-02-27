package edu.gatech.seclass.crypto6300.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM Users WHERE category is NOT NULL")
    fun getPlayers(): LiveData<List<User>>

    @Query("SELECT * FROM Users WHERE id = :userId LIMIT 1")
    fun getById(userId: String): LiveData<User>

    @Query("SELECT * FROM Users WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByLoginInfo(username: String, password: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Query("DELETE FROM Users where category is NOT NULL")
    fun deleteAllPlayers()
}