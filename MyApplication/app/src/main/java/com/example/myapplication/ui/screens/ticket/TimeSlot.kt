package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimeSlot(
    onTimeSlotSelect: (String) -> Unit,
    time: String
) {
    OutlinedButton(
        modifier = Modifier.padding(end = 8.dp),
        onClick = {
            onTimeSlotSelect(time)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(text = time)
    }
}