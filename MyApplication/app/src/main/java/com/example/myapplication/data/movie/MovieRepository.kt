package com.example.myapplication.data.movie

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAll(): Flow<List<Movie>>
    fun findMovieById(id: Int): Flow<Movie?>
}