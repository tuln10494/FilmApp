package com.example.myapplication.ui.screens.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.enum.LoadStatus
import com.example.myapplication.data.movie.Movie
import com.example.myapplication.data.movie.MovieRepository
import com.example.myapplication.repositories.HomeRepository.HomeApi
import com.example.myapplication.repositories.MainLog
import com.example.myapplication.repositories.StoreValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val status: LoadStatus = LoadStatus.Init(),
)

@HiltViewModel
class HomeViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies
    val _currentSelectedMovie = MutableStateFlow<Movie?>(null)
    val currentSelectedMovie: MutableStateFlow<Movie?> = _currentSelectedMovie

    private val movieRes = movieRepository

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRes.getAll().collect { movieList ->
                    _movies.value = movieList
                }
        }
    }

    fun selectMovie(movie : Movie){
        _currentSelectedMovie.value = movie
    }

    private fun setState(state: LoadStatus) {
        _uiState.value = _uiState.value.copy(status = state)
    }

    private fun reset() {
        _uiState.value = _uiState.value.copy(status = LoadStatus.Init())
    }

}