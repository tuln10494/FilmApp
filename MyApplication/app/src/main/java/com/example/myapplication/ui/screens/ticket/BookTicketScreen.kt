package com.example.myapplication.ui.screens.ticket

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapplication.Screen
import com.example.myapplication.ui.screens.shared.TicketBookingSharedViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookTicketScreen(
    navController: NavController,
    sharedViewModel: TicketBookingSharedViewModel
) {
    val movie by sharedViewModel
        .selectedMovie.collectAsStateWithLifecycle(null)
    val selectedDate by sharedViewModel
        .selectedDate.collectAsStateWithLifecycle(null)

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, "Back button")
                }
            },
            title = { Text(text = "${movie?.name}") }
        )
    }) { innerPadding ->
        movie?.let {
            Column(modifier = Modifier.padding(innerPadding)) {
                ScrollableDatePickerRow(
                    selectedDate = selectedDate ?: LocalDate.now(),
                    onDateSelected = { date ->
                        sharedViewModel.setSelectedDate(date)
                    }
                )
                ExpandableCinemaList(
                    onTimeSlotSelected = { cinema, timeSlot ->
                        sharedViewModel.setSelectedCinema(cinema)
                        sharedViewModel.setSelectedHour(timeSlot)
                        navController.navigate(Screen.SeatsSelection.route)
                    }
                )
            }
        }
    }
}