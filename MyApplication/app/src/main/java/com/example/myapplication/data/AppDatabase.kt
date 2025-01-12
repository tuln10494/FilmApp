package com.example.myapplication.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.user.UserInfo

@Database(entities = [Center::class,UserInfo::class], version = 2)
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