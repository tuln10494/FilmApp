package com.example.myapplication.screens.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.repositories.MainLog
import com.example.myapplication.repositories.StoreValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class HomeUiState(
    val test : String
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val log: MainLog?,
    private val storeValue: StoreValue?
) : ViewModel() {
    private val tag = "HomeViewModel"
    val _uiState = MutableStateFlow(HomeUiState(""))
    val uiState = _uiState.asStateFlow()

    fun updateTest(s:String) {
        _uiState.value = _uiState.value.copy(test = s)
    }

    override fun onCleared() {
        log?.i(tag,"HomeViewModel on cleared")

    }
}