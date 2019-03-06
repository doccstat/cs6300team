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
            "SELECT "
                    + "Cryptogram.id AS cryptogram_id, "
                    + "Cryptogram.name AS name, "
                    + "ca.attempts_remaining AS attempts_remaining, "
                    + "Cryptogram.solution AS solution, "
                    + "Cryptogram.difficulty AS difficulty, "
                    + "Cryptogram.easy AS easy, "
                    + "Cryptogram.normal AS normal, "
                    + "Cryptogram.hard AS hard, "

                    + "(CASE WHEN ca.is_completed = 1 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END) AS is_completed, "
                    + "(CASE WHEN ca.is_solved = 1 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END) AS is_solved "

                    + "FROM Cryptogram "
                    + "LEFT OUTER JOIN ("
                    + "SELECT * FROM CryptogramAttempt WHERE user_id = :playerId"
                    + ") ca "
                    + "ON Cryptogram.id = ca.cryptogram_id")
    fun getAttemptsAndUnsolvedCryptogramsForPlayer(playerId: String): LiveData<List<ChooseCryptogram>>

    @Query("SELECT * FROM CryptogramAttempt WHERE user_id = :userId")
    fun getAllAttemptsForPlayer(userId: String): LiveData<List<CryptogramAttempt>>

    @Query("SELECT * FROM CryptogramAttempt WHERE cryptogram_id = :cryptogramId")
    fun getAllAttemptsForCryptogram(cryptogramId: String): LiveData<List<CryptogramAttempt>>

    @Query("SELECT * FROM CryptogramAttempt WHERE user_id = :playerId AND cryptogram_id = :cryptogramId LIMIT 1")
    fun getAttemptByUserIdAndCryptogramId(playerId: String, cryptogramId: String): LiveData<CryptogramAttempt>

    @Query("SELECT * FROM CryptogramAttempt WHERE id = :cryptogramAttemptId LIMIT 1")
    fun getAttemptById(cryptogramAttemptId: String): LiveData<CryptogramAttempt>

    @Query(
            "SELECT "
                    + "CASE WHEN EXISTS("
                    + "SELECT * FROM Cryptogram INNER JOIN CryptogramAttempt "
                    + "ON Cryptogram.id = CryptogramAttempt.cryptogram_id "
                    + "WHERE CryptogramAttempt.id = :attemptId AND Cryptogram.solution = :solution LIMIT 1) "
                    + "THEN CAST(1 AS BIT)"
                    + "ELSE CAST(0 AS BIT) END"
    )
    fun checkSolutionForAttempt(attemptId: String, solution: String): LiveData<Boolean>

    @Query("UPDATE CryptogramAttempt "
            + "SET attempts_remaining = attempts_remaining - 1, "
                + "submission = :submission, "
                + "is_solved = :isSolved, "
                + "is_completed = (CASE WHEN attempts_remaining < 1 OR :isSolved = 1 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END)"
            + "WHERE CryptogramAttempt.id = :attemptId AND attempts_remaining > 0")
    fun updateAttemptForTry(attemptId: String, submission: String, isSolved: Boolean)

    @Query("SELECT CASE WHEN is_completed = 1 THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END FROM CryptogramAttempt WHERE id = :attemptId LIMIT 1")
    fun checkIfAttemptCompleted(attemptId: String) : LiveData<Boolean>

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