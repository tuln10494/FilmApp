package com.example.myapplication.data.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movie_id")
    val id: Int = 0,

    @ColumnInfo(name = "movie_name")
    val name: String,

    @ColumnInfo(name = "image_name")
    val imageName: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "actors")
    val actors: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String
)