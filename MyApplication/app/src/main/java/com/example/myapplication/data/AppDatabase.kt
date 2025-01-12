package com.example.myapplication.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Center::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val centerDao: CenterDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(app: Application): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        app.applicationContext,
                        AppDatabase::class.java,
                        "movies_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}