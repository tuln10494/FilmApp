package com.example.myapplication.data.center

import kotlinx.coroutines.flow.Flow

class CenterRepositoryImpl(private val centerDao: CenterDao) : CenterRepository {
    override fun getAll(): Flow<List<Center>> {
        return centerDao.getAll()
    }

    override suspend fun insert(center: Center) {
        centerDao.insert(center)
    }
}