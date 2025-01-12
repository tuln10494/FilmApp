package com.example.myapplication.data.user

import com.example.myapplication.data.CenterDao
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUser(): Flow<List<UserInfo>> {
        return userDao.getAllUser()
    }

    override suspend fun insert(userInfo: UserInfo) {
       userDao.insert(userInfo)
    }
}