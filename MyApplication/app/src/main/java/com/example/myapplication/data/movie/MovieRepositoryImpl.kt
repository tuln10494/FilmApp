package com.example.myapplication.data.movie

import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieRepository {
    override fun getAll(): Flow<List<Movie>> {
        return movieDao.getAll()
    }

    override fun findMovieById(id: Int): Flow<Movie?> {
        return movieDao.findMovieById(id)
    }
}