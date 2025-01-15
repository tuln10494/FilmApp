package com.example.myapplication.ui.screens.seats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.myapplication.models.seatselection.SeatsRowData


@Composable
fun SeatsRow(seatsRowData: SeatsRowData) {
    var remainingSeats = seatsRowData.numberSeats
    var currentSeat = 1
    Row(horizontalArrangement = Arrangement.End) {
        while (remainingSeats > 0) {
            if (seatsRowData.voidSeats.contains(currentSeat)) {
                VoidSeatItem()
                currentSeat++
            } else {
                SeatItem(
                    rowName = seatsRowData.rowName,
                    number = seatsRowData.numberSeats - remainingSeats + 1,
                    seatType = seatsRowData.seatType
                )
                currentSeat++
                remainingSeats--
            }
        }
    }
}