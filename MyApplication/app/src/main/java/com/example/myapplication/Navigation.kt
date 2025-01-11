package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.home.HomeScreen
import com.example.myapplication.screens.home.HomeViewModel
import com.example.myapplication.screens.login.LoginScreen

//Create route for your screen here

sealed class Screen(val route : String){
    object Home :Screen("home_screen")
    object Login :Screen("login_screen")
    object Detail :Screen("film_detail_Screen")
    object Register :Screen("register_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel : MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            HomeScreen(navController, hiltViewModel(),mainViewModel)
        }
        composable(Screen.Login.route){
            // test get parent entry

//            val parentEntry = remember (it){
//                navController.getBackStackEntry(Screen.Home.route)
//            }
//            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)

            LoginScreen(navController, hiltViewModel())
        }
    }
}