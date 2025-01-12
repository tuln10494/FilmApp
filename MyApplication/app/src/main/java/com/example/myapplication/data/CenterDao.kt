package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CenterDao {
    @Query("SELECT * FROM center_table")
    fun getAll(): Flow<List<Center>>

    @Insert
    suspend fun insert(center: Center)
}