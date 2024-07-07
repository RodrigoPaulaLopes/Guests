package com.rodrigo.convidados.repository.guest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rodrigo.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {


    abstract fun guestDao() : GuestDAO
    companion object {
        lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context): GuestDatabase {
            synchronized(GuestDatabase::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDatabase::class.java, "guestdb")
                            .addMigrations(MIGRATIONS)
                            .allowMainThreadQueries()
                            .build()

                }
                return INSTANCE
            }
        }

        private val MIGRATIONS : Migration = object  : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
               database.execSQL("SELECT * FROM guests;")
            }

        }
    }
}