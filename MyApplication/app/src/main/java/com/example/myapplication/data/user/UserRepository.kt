package com.example.myapplication.data.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUser() : Flow<List<UserInfo>>
    suspend fun insert(userInfo: UserInfo)
    suspend fun getUserByEmailOrPhone(emailOrPhone: String): UserInfo?
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserInfo?
}