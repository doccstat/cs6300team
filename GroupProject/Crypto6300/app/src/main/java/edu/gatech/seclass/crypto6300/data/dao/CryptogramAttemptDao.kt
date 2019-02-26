package edu.gatech.seclass.crypto6300.data.dao

import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt

@Dao
interface CryptogramAttemptDao {
    @Query("SELECT * FROM CryptogramAttempt")
    fun getAllCryptogramAttempts(): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE userId = :userId")
    fun getAllAttemptsForPlayer(userId: String): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE cryptogramId = :cryptogramId")
    fun getAllAttemptsForCryptogram(cryptogramId: String): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE id = :id LIMIT 1")
    fun getAttemptById(id: String)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAttempt(cryptogram: CryptogramAttempt): Long

    @Update
    fun updateAttempt(attempt: CryptogramAttempt)

    @Delete
    fun deleteAttempt(cryptogram: CryptogramAttempt)

    @Query("DELETE FROM CryptogramAttempt")
    fun deleteAllAttempts()

    @Query("DELETE FROM CryptogramAttempt WHERE userId = :userId")
    fun deleteAllAttemptsForPlayer(userId: String)

    @Query("DELETE FROM CryptogramAttempt WHERE cryptogramId = :cryptogramId")
    fun deleteAllAttemptsForCryptogram(cryptogramId: String)
}