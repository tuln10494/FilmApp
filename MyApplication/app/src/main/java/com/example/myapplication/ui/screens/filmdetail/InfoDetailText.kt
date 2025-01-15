package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.movie.Movie

@Composable
fun InfoDetailText(movie: Movie) {
    Column (
        modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.25f)
    ) {
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "The loai", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.genre, modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Dao dien", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.director, modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Dien vien", style = MaterialTheme.typography.titleMedium)
            Text(text = movie.actors, modifier = Modifier.offset(x = 8.dp))
        }
    }
}