package com.example.myapplication.ui.screens.ticket

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScrollableDatePickerRow(
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit
) {
    // Default date list 30 days
    val dateList = List(30) { LocalDate.now().plusDays(it.toLong()) }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Scrollable Row of Dates
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dateList.size) { index ->
                val date = dateList[index]
                val isSelected = date == selectedDate

                // Text component to display each date
                Text(
                    text = date.format(DateTimeFormatter.ofPattern("MMM dd")),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = if (isSelected) Color.White else Color.Black
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .let {
                            if (isSelected) it.background(Color.Blue).padding(16.dp) else it
                        }
                        .clickable {
                            onDateSelected(date)
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected date
        Text(
            text = "Selected Date: ${selectedDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}