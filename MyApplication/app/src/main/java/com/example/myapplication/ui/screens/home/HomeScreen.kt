package com.example.myapplication.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.myapplication.MainViewModel
import com.example.myapplication.Screen
import com.example.myapplication.ui.screens.home.HomeAppBar

@Composable
fun HomeScreen(navController: NavHostController, hiltViewModel: HomeViewModel, mainViewModel: MainViewModel) {
    val state = hiltViewModel.uiState.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar()
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
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
}

@Preview
@Composable
fun HomeScreenPreview(){
    // need make argument of ViewModel as Nullable to see Preview
    HomeScreen(navController = NavHostController(LocalContext.current),
        hiltViewModel = HomeViewModel(null,null, null), mainViewModel = MainViewModel()
    )
}