package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier

@Composable
fun ExpandableList(onTimeSlotSelect: () -> Unit) {
    // Sample data for the list
    val items = listOf(
        ListItem("CGV Vincom Sky Lake", "This is the content for item 1"),
        ListItem("CGV Indochina Plaza", "This is the content for item 2"),
        ListItem("CGV Tran Duy Hung", "This is the content for item 1"),
        ListItem("CGV Aeon Mall", "This is the content for item 2"),
        ListItem("CGV Nguyen Chi Thanh", "This is the content for item 1"),
        ListItem("CGV Bac Tu Liem", "This is the content for item 2"),
        ListItem("CGV Ho Guom Plaza", "This is the content for item 1"),
        ListItem("CGV Xuan Dieu", "This is the content for item 2"),
        ListItem("CGV Trang Tien", "This is the content for item 3")
    )

    // Keep track of which items are expanded
    val expandedStates =
        remember { mutableStateListOf<Boolean>().apply { repeat(items.size) { add(false) } } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        itemsIndexed(items) { index, item ->
            ExpandableListItem(
                item = item,
                isExpanded = expandedStates[index],
                onToggleExpanded = {
                    // Toggle the expanded state for the clicked item
                    expandedStates[index] = !expandedStates[index]
                },
                onTimeSlotSelect = onTimeSlotSelect
            )
        }
    }
}