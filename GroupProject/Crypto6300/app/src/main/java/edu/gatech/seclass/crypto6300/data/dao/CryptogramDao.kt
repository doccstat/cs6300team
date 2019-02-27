package edu.gatech.seclass.crypto6300.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram

@Dao
interface CryptogramDao {
    @Query("SELECT * FROM Cryptogram")
    fun getAllCryptograms() : LiveData<List<Cryptogram>>

    @Query("SELECT * FROM Cryptogram WHERE difficulty = :difficulty")
    fun getAllCryptogramsByDifficulty(difficulty: String): LiveData<List<Cryptogram>>

    @Query("SELECT * FROM Cryptogram WHERE id = :cryptogramId LIMIT 1")
    fun getById(cryptogramId: String): LiveData<Cryptogram>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptogram: Cryptogram): Long

    @Delete
    fun delete(cryptogram: Cryptogram)

    @Query("DELETE FROM Cryptogram")
    fun deleteAllCryptograms()
}