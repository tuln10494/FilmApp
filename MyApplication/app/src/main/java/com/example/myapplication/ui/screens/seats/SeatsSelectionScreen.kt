package com.example.myapplication.ui.screens.seats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.models.seatselection.SeatType
import com.example.myapplication.models.seatselection.SeatsRowData
import com.example.myapplication.ui.screens.shared.TicketBookingSharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatsSelectionScreen(
    navController: NavController,
    ticketBookingSharedViewModel: TicketBookingSharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, "Back button")
                    }
                },
                title = { Text(text = "Select Seats") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MainContent()
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val layout2: List<SeatsRowData> = listOf(
        SeatsRowData(rowName = "A", numberSeats = 22, voidSeats = setOf(5, 20)),
        SeatsRowData(rowName = "B", numberSeats = 22, voidSeats = setOf(5, 20)),
        SeatsRowData(rowName = "C", numberSeats = 22, voidSeats = setOf(5, 20)),
        SeatsRowData(
            rowName = "D",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "E",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "F",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "G",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "H",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "I",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "J",
            numberSeats = 22,
            voidSeats = setOf(5, 20),
            seatType = SeatType.VIP
        ),
        SeatsRowData(
            rowName = "K",
            numberSeats = 22,
            voidSeats = setOf(1),
            seatType = SeatType.COUPLE
        )
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        SeatsLayout(layout2, true)
    }
}