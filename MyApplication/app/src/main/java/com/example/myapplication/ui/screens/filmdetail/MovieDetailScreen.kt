package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication.Screen
import com.example.myapplication.ui.screens.shared.TicketBookingSharedViewModel

@Composable
fun MovieDetailScreen(
    navController: NavController,
    sharedViewModel: TicketBookingSharedViewModel
) {
    val movie by sharedViewModel.selectedMovie.collectAsStateWithLifecycle(initialValue = null)
    movie?.let {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OverlappingImages(it)
            Spacer(Modifier.height(2.dp))
            SeeMoreTextExample(it.description)
            Spacer(Modifier.height(2.dp))
            InfoDetailText(it)
            Spacer(Modifier.height(2.dp))
            BookTicketButton {
                navController.navigate(Screen.BookTicket.route)
            }
        }
    }

    if (movie == null) {
        Text("Loading...")
    }
}











