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


@Database(entities = [Cryptogram::class, CryptogramAttempt::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cryptogramDao(): CryptogramDao
    abstract fun cryptogramAttemptDao(): CryptogramAttemptDao

    companion object {
        private val adminUser = User(
                firstName = "admin",
                lastName = "admin",
                username = "admin",
                password = "admin",
                category = null,
                wins = 0,
                losses = 0
        )

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = buildDatabase(context)
                    instance?.prepopulateDb()
                }
            }

            return instance
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "crypto6300db")
                    .build()
        }

        fun destroyDb() {
            instance = null
        }
    }

    private fun prepopulateDb() {
        Thread(Runnable {
            if (userDao().getAllUsers().value.isNullOrEmpty()) {
                this.userDao().insert(adminUser)
            }
        }).start()
    }
}