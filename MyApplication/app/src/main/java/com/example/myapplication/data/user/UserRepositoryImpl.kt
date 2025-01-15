package com.example.myapplication.data.user

import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUser(): Flow<List<UserInfo>> {
        return userDao.getAllUser()
    }

    override suspend fun insert(userInfo: UserInfo) {
        userDao.insert(userInfo)
    }
    override suspend fun getUserByEmailOrPhone(emailOrPhone: String): UserInfo? {
        return userDao.getUserByEmailOrPhone(emailOrPhone)
    }
    override suspend fun getUserByEmailAndPassword(email: String, password: String): UserInfo? {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    override suspend fun getUserById(userId: Int): UserInfo? {
        return userDao.getUserById(userId)
    }

    override suspend fun update(userInfo: UserInfo) {
        userDao.update(userInfo)
    }
}