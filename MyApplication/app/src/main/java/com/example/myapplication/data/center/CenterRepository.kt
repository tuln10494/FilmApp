package com.example.myapplication.data.center

import kotlinx.coroutines.flow.Flow

interface CenterRepository {
    fun getAll(): Flow<List<Center>>
    suspend fun insert(center: Center)
}