package edu.gatech.seclass.crypto6300.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.gatech.seclass.crypto6300.data.dao.CryptogramAttemptDao
import edu.gatech.seclass.crypto6300.data.dao.CryptogramDao
import edu.gatech.seclass.crypto6300.data.dao.UserDao
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt
import edu.gatech.seclass.crypto6300.data.entities.User

@Database(entities = [Cryptogram::class, CryptogramAttempt::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cryptogramDao(): CryptogramDao
    abstract fun cryptogramAttemptDao(): CryptogramAttemptDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDb(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "crypto6300db").build()
                }
            }

            return INSTANCE
        }

        fun destroyDb() {
            INSTANCE = null
        }
    }
}