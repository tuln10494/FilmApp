package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.home.HomeScreen
import com.example.myapplication.ui.screens.login.LoginScreen
import com.example.myapplication.ui.screens.login.RegisterScreen
import com.example.myapplication.ui.screens.seats.SeatsSelectionScreen

//Create route for your screen here

sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Login : Screen("login_screen")
    data object Detail : Screen("film_detail_screen")
    data object Register : Screen("register_screen")
    data object SeatsSelection : Screen("seats_selection_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Register.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, hiltViewModel(), mainViewModel)
        }
        composable(Screen.Login.route) {
            // test get parent entry

//            val parentEntry = remember (it){
//                navController.getBackStackEntry(Screen.Home.route)
//            }
//            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)

            LoginScreen(navController, hiltViewModel())
        }
        composable(Screen.Register.route){
            RegisterScreen()
        }
        composable(Screen.SeatsSelection.route) {
            SeatsSelectionScreen()
        }
    }
}