package edu.gatech.seclass.crypto6300.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt

@Dao
interface CryptogramAttemptDao {

    @Query("SELECT * FROM CryptogramAttempt")
    fun getAllCryptogramAttempts(): LiveData<List<CryptogramAttempt>>

    @Query(
            "SELECT Cryptogram.id AS cryptogram_id, Cryptogram.name AS name, "
                    + "Cryptogram.difficulty AS difficulty, CryptogramAttempt.is_completed AS in_progress "
                    + "FROM CryptogramAttempt "
            + "LEFT OUTER JOIN Cryptogram ON Cryptogram.id = CryptogramAttempt.cryptogram_id "
            + "WHERE CryptogramAttempt.user_id = :playerId AND CryptogramAttempt.is_completed = 'false'"
    )
    fun getCryptogramsAndAttemptsForPlayer(playerId: String): LiveData<List<ChooseCryptogram>>

    @Query(

            "SELECT Cryptogram.id AS cryptogram_id, Cryptogram.name AS name, "
                    + "Cryptogram.difficulty AS difficulty, CryptogramAttempt.is_completed AS in_progress "
                    + "FROM Cryptogram "
            + "INNER JOIN CryptogramAttempt ON Cryptogram.id = CryptogramAttempt.cryptogram_id WHERE user_id != :playerId")
    fun getUnsolvedCryptogramsForPlayer(playerId: String): LiveData<List<ChooseCryptogram>>

    @Query("SELECT * FROM CryptogramAttempt WHERE user_id = :userId")
    fun getAllAttemptsForPlayer(userId: String): LiveData<List<CryptogramAttempt>>

    @Query("SELECT * FROM CryptogramAttempt WHERE cryptogram_id = :cryptogramId")
    fun getAllAttemptsForCryptogram(cryptogramId: String): LiveData<List<CryptogramAttempt>>

    @Query("SELECT * FROM CryptogramAttempt WHERE id = :cryptogramAttemptId LIMIT 1")
    fun getAttemptById(cryptogramAttemptId: String): LiveData<CryptogramAttempt>

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