package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.Screen

@Composable
fun BookTicketScreen(navController: NavController) {
    Column {
        TitleAndMenu()
        ImageCover()
        ExpandableList(
            onTimeSlotSelect = { navController.navigate(Screen.SeatsSelection.route) }
        )
    }
}