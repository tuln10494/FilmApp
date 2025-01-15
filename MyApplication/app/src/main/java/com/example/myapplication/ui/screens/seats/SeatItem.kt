package com.example.myapplication.ui.screens.seats

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.models.seatselection.SeatType

@Composable
fun SeatItem(
    rowName: String,
    number: Int,
    seatType: SeatType = SeatType.NORM
) {
    var isSelected by remember { mutableStateOf(false) }
    var background by remember { mutableStateOf(Color.Green) }
    background = when (seatType) {
        SeatType.NORM -> Color.Green
        SeatType.VIP -> Color.Red
        SeatType.COUPLE -> Color.Cyan
    }
    if (isSelected) {
        background = Color.Gray
    }
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 12.dp, height = 12.dp)
            .background(background)
            .clickable { isSelected = !isSelected },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$rowName$number",
            style = TextStyle(fontSize = 6.sp)
        )
    }
}