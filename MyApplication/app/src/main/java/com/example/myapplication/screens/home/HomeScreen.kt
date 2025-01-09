package com.example.myapplication.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.myapplication.Screen
import com.example.myapplication.repositories.StoreValue

@Composable
fun HomeScreen(navController: NavHostController, hiltViewModel: HomeViewModel) {
    val state = hiltViewModel.uiState.collectAsState()
    Column {
        ElevatedButton(onClick = {
            navController.navigate(Screen.Login.route)
        }) {
            Text("HomeScreen")
        }
        Text(text = state.value.test)

        ElevatedButton(onClick = {
            hiltViewModel.updateTest("hehe")
        }) {
            Text("Edit String Test")
        }
    }
}