package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication.Screen

@Composable
fun MovieDetailScreen(navController: NavController, viewModel: MovieDetailViewModel, movieId: Int) {
    val movieFlow = viewModel.findMovieById(movieId).collectAsStateWithLifecycle(initialValue = null)
    val movie = movieFlow.value
    if (movie != null) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OverlappingImages(movie = movie)
            Spacer(modifier = Modifier.height(2.dp))
            SeeMoreTextExample(movie.description)
            Spacer(modifier = Modifier.height(2.dp))
            InfoDetailText(movie = movie)
            Spacer(modifier = Modifier.height(2.dp))
            BookTicketButton(onClick = { navController.navigate(Screen.BookTicket.route) })
        }
    } else {
        // Handle the case where the movie is not found
        Text("Loading...")
    }
}











