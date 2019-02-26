package edu.gatech.seclass.crypto6300.data.dao

import androidx.room.*
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram

@Dao
interface CryptogramDao {
    @Query("SELECT * FROM Cryptogram")
    fun getAllCryptograms() : List<Cryptogram>

    @Query("SELECT * FROM Cryptogram WHERE id = :cryptogramId LIMIT 1")
    fun getById(cryptogramId: String): Cryptogram

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cryptogram: Cryptogram): Long

    @Delete
    fun delete(cryptogram: Cryptogram)

    @Query("DELETE FROM Cryptogram")
    fun deleteAllCryptograms()
}