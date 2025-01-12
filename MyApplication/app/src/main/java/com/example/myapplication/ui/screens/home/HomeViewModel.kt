package com.example.myapplication.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.common.enum.LoadStatus
import com.example.myapplication.data.movie.MovieRepository
import com.example.myapplication.repositories.HomeRepository.HomeApi
import com.example.myapplication.repositories.MainLog
import com.example.myapplication.repositories.StoreValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUiState(
    val test : String,
    val status : LoadStatus = LoadStatus.Init()
)

@HiltViewModel
class HomeViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    val movies = movieRepository.getAll()
}