package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.filmdetail.MovieDetailScreen
import com.example.myapplication.ui.screens.home.HomeScreen
import com.example.myapplication.ui.screens.login.LoginScreen
import com.example.myapplication.ui.screens.profile.EditProfileScreen
import com.example.myapplication.ui.screens.profile.ProfileScreen
import com.example.myapplication.ui.screens.seats.SeatsSelectionScreen
import com.example.myapplication.ui.screens.shared.TicketBookingSharedViewModel
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
    data object Main : Screen("main")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.EditProfile.route) {
            EditProfileScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController, hiltViewModel())
        }
        navigation(startDestination = Screen.Home.route, route = Screen.Main.route) {
            composable(Screen.Home.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screen.Main.route)
                }
                val ticketBookingSharedViewModel =
                    hiltViewModel<TicketBookingSharedViewModel>(parentEntry)
                HomeScreen(
                    navController = navController,
                    homeViewModel = hiltViewModel(),
                    sharedViewModel = ticketBookingSharedViewModel
                )
            }
            composable(Screen.MovieDetail.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screen.Main.route)
                }
                val ticketBookingSharedViewModel =
                    hiltViewModel<TicketBookingSharedViewModel>(parentEntry)
                MovieDetailScreen(
                    navController = navController,
                    sharedViewModel = ticketBookingSharedViewModel
                )
            }
            composable(Screen.BookTicket.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screen.Main.route)
                }
                val ticketBookingSharedViewModel =
                    hiltViewModel<TicketBookingSharedViewModel>(parentEntry)

                BookTicketScreen(
                    navController = navController,
                    sharedViewModel = ticketBookingSharedViewModel
                )
            }
            composable(Screen.SeatsSelection.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Screen.Main.route)
                }
                val ticketBookingSharedViewModel =
                    hiltViewModel<TicketBookingSharedViewModel>(parentEntry)
                SeatsSelectionScreen(
                    navController = navController,
                    ticketBookingSharedViewModel = ticketBookingSharedViewModel
                )
            }
        }

    }
}