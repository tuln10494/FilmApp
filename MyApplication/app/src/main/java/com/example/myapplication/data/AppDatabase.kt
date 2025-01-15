package com.example.myapplication.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.center.Center
import com.example.myapplication.data.center.CenterDao
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.data.movie.MovieDao
import com.example.myapplication.data.user.UserInfo

@Database(entities = [Center::class, UserInfo::class, Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val centerDao: CenterDao
    abstract val movieDao: MovieDao

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
                        .createFromAsset("database/movies.db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}