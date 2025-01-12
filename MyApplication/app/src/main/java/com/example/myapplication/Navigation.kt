package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screens.home.HomeScreen
import com.example.myapplication.ui.screens.login.LoginScreen
import com.example.myapplication.ui.screens.seats.SeatsSelectionScreen
import com.example.myapplication.ui.screens.profile.EditProfileScreen
import com.example.myapplication.ui.screens.profile.ProfileScreen
import com.example.myapplication.ui.screens.profile.UserViewModel

//Create route for your screen here

sealed class Screen(val route : String){
    object Home :Screen("home_screen")
    object Login :Screen("login_screen")
    object Detail :Screen("film_detail_Screen")
    object Register :Screen("register_screen")
    object Profile :Screen("profile_screen")
    object EditProfile :Screen("edit_profile_screen")
    data object SeatsSelection : Screen("seats_selection_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, hiltViewModel(), mainViewModel)
        }
        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }
        composable(Screen.EditProfile.route){
            EditProfileScreen(navController)
        }
        composable(Screen.Login.route) {
            // test get parent entry

//            val parentEntry = remember (it){
//                navController.getBackStackEntry(Screen.Home.route)
//            }
//            val homeViewModel = hiltViewModel<HomeViewModel>(parentEntry)

            LoginScreen(navController, hiltViewModel())
        }
        composable(Screen.SeatsSelection.route) {
            SeatsSelectionScreen()
        }
    }
}