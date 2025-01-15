package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.home.HomeScreen
import com.example.myapplication.ui.screens.filmdetail.MovieDetailScreen
import com.example.myapplication.ui.screens.login.LoginScreen
import com.example.myapplication.ui.screens.login.RegisterScreen
import com.example.myapplication.ui.screens.seats.SeatsSelectionScreen
import com.example.myapplication.ui.screens.profile.EditProfileScreen
import com.example.myapplication.ui.screens.profile.ProfileScreen
import com.example.myapplication.ui.screens.ticket.BookTicketScreen

//Create route for your screen here

sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Login : Screen("login_screen")
    data object MovieDetail : Screen("movie_detail_screen")
    data object Register : Screen("register_screen")
    data object Profile : Screen("profile_screen")
    data object EditProfile : Screen("edit_profile_screen")
    data object BookTicket : Screen("book_ticket_screen")
    data object SeatsSelection : Screen("seats_selection_screen")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Register.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController, hiltViewModel())
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.EditProfile.route) {
            EditProfileScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController, hiltViewModel())
        }
        composable(Screen.Register.route){
            RegisterScreen(navController,hiltViewModel())
        }

        composable(Screen.BookTicket.route) {
            BookTicketScreen(navController)
        }
        composable(Screen.SeatsSelection.route) {
            SeatsSelectionScreen()
        }
        composable(
            route = "${Screen.MovieDetail.route}/{movieId}", arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) {
            MovieDetailScreen(
                navController = navController,
                viewModel = hiltViewModel(),
                movieId = it.arguments?.getInt("movieId") ?: 0
            )
        }
    }
}