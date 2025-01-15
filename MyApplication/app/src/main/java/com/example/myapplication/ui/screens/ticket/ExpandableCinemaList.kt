package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import com.example.myapplication.models.ticket.Cinema

@Composable
fun ExpandableCinemaList(onTimeSlotSelected: (Cinema, String) -> Unit) {
    // Sample data for the list
    val cinemas = listOf(
        Cinema(1, "CGV Vincom Sky Lake", "", listOf("10:30", "12:30", "14:30")),
        Cinema(2, "CGV Indochina Plaza", "", listOf("10:30", "12:30", "14:30")),
        Cinema(3, "CGV Tran Duy Hung", "", listOf("10:30", "12:30", "14:30")),
        Cinema(4, "CGV Aeon Mall", "", listOf("10:30", "12:30", "14:30")),
        Cinema(5, "CGV Vincom Sky Lake", "", listOf("10:30", "12:30", "14:30"))
    )

    // Keep track of which items are expanded
    val expandedStates =
        remember { mutableStateListOf<Boolean>().apply { repeat(cinemas.size) { add(false) } } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        itemsIndexed(cinemas) { index, item ->
            ExpandableCinemaListItem(
                cinema = item,
                isExpanded = expandedStates[index],
                onToggleExpanded = {
                    // Toggle the expanded state for the clicked item
                    expandedStates[index] = !expandedStates[index]
                },
                onTimeSlotSelect = { timeSlot ->
                    onTimeSlotSelected(item, timeSlot)
                }
            )
        }
    }
}