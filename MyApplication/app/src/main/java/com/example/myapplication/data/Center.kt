package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "center_table")
data class Center(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "center_id")
    val id: Int = 0,

    @ColumnInfo(name = "center_name")
    val centerName: String,
)