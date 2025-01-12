package com.example.myapplication.data.movie

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies_table")
    fun getAll(): Flow<List<Movie>>
}