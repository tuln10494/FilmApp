package com.example.myapplication.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info_table")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int = 0,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_phone_number")
    val userPhoneNumber: String,

    @ColumnInfo(name = "user_email")
    val email: String,

    @ColumnInfo(name = "user_password")
    val passWord: String,

    @ColumnInfo(name = "user_birthday")
    val userBirthday: String,

    @ColumnInfo(name = "user_gender")
    val userGender: Int,

    @ColumnInfo(name = "user_favourite_cinema")
    val userFavoriteCinema: String,

    @ColumnInfo(name = "user_province")
    val userProvince: String,

    @ColumnInfo(name = "user_district")
    val userDistrict: String,
)
