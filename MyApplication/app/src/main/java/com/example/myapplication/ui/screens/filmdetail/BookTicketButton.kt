package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Screen

@Composable
fun BookTicketButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Fills the entire screens or parent
            .padding(8.dp), // Optional padding for spacing
        verticalArrangement = Arrangement.Center, // Centers vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers horizontally
    ) {
        OutlinedButton(onClick = {
            navController.navigate(Screen.SeatsSelection.route)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color.Red
            )) {
            Text(text = "Đặt Vé")
        }
    }
}