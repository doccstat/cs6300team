package edu.gatech.seclass.crypto6300.data.dao

import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt

@Dao
interface CryptogramAttemptDao {
    @Query("SELECT * FROM CryptogramAttempt")
    fun getAllCryptogramAttempts(): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE user_id = :userId")
    fun getAllAttemptsForPlayer(userId: String): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE cryptogram_id = :cryptogramId")
    fun getAllAttemptsForCryptogram(cryptogramId: String): List<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE id = :cryptogramAttemptId LIMIT 1")
    fun getAttemptById(cryptogramAttemptId: String): CryptogramAttempt

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertAttempt(cryptogram: CryptogramAttempt): Long

    @Update
    fun updateAttempt(attempt: CryptogramAttempt)

    @Delete
    fun deleteAttempt(cryptogram: CryptogramAttempt)

    @Query("DELETE FROM CryptogramAttempt")
    fun deleteAllAttempts()

    @Query("DELETE FROM CryptogramAttempt WHERE user_id = :userId")
    fun deleteAllAttemptsForPlayer(userId: String)

    @Query("DELETE FROM CryptogramAttempt WHERE cryptogram_id = :cryptogramId")
    fun deleteAllAttemptsForCryptogram(cryptogramId: String)
}