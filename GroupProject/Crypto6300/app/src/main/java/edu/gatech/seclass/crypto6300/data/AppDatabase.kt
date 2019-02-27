package edu.gatech.seclass.crypto6300.data

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
        private var INSTANCE: AppDatabase? = null

        fun getAppDb(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "crypto6300db")
                            .addCallback(object : RoomDatabase.Callback() {

                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)

                                    prepopulateDb(context.applicationContext as Application)
                                }
                            })
                            .build()
                }
            }

            return INSTANCE
        }

        private fun prepopulateDb(application: Application) {
            val db = getAppDb(application)
            db?.userDao()?.let {
                PopulateDbAsync(it).execute()
            }
        }

        class PopulateDbAsync(val userDao: UserDao) : AsyncTask<Unit, Unit, Unit>() {

            override fun doInBackground(vararg params: Unit?) {
                val adminUser = User(
                        firstName = "admin",
                        lastName = "admin",
                        username = "admin",
                        password = "admin",
                        category = null,
                        wins = 0,
                        losses = 0
                )
                userDao.insert(adminUser)
            }
        }

        fun destroyDb() {
            INSTANCE = null
        }
    }
}