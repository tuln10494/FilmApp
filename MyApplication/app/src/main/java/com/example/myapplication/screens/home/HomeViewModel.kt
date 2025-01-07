package com.example.myapplication.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUiState(
    val test : String
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val _uiState = MutableStateFlow(HomeUiState(""))
    val uiState = _uiState.asStateFlow()

    fun updateTest(s:String) {
        _uiState.value = _uiState.value.copy(test = s)
    }

    override fun onCleared() {
        println("HomeViewModel on cleard")
        super.onCleared()
    }
}