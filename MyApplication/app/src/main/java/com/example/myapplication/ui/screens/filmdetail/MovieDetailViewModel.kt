package com.example.myapplication.ui.screens.filmdetail

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.data.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    fun findMovieById(movieId: Int): Flow<Movie?> {
        return movieRepository.findMovieById(movieId)
            .catch { emit(null) }
    }
}