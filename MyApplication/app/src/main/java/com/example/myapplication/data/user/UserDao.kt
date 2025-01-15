package com.example.myapplication.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_info_table")
    fun getAllUser() : Flow<List<UserInfo>>

    @Insert
    suspend fun insert(user: UserInfo)
    @Query("SELECT * FROM user_info_table WHERE user_name = :emailOrPhone OR user_phone_number = :emailOrPhone LIMIT 1")
    suspend fun getUserByEmailOrPhone(emailOrPhone: String): UserInfo?
    @Query("SELECT * FROM user_info_table WHERE user_email = :email AND user_password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserInfo?
    @Query("SELECT * FROM user_info_table WHERE user_id = :userId")
    suspend fun getUserById(userId: Int): UserInfo?

    @Update
    suspend fun update(userInfo: UserInfo)
}