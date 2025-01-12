package com.example.myapplication.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_info_table")
    fun getAllUser() : Flow<List<UserInfo>>

    @Insert
    suspend fun insert(user: UserInfo)
}