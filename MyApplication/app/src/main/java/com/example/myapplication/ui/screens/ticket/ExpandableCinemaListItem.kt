package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.ticket.Cinema

@Composable
fun ExpandableCinemaListItem(
    cinema: Cinema,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit,
    onTimeSlotSelect: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onToggleExpanded),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cinema.name,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            if (isExpanded) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    items(cinema.timeSlots) { timeSlot ->
                        TimeSlot(
                            onTimeSlotSelect = onTimeSlotSelect,
                            time = timeSlot
                        )
                    }
                }
            }
        }
    }
}