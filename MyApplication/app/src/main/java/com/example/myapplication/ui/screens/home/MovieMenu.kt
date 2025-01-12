package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp

@Composable
fun MovieMenu() {
    val textStyle = TextStyle(color = Color.White, fontStyle = FontStyle.Normal)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Đang chiếu", style = textStyle)
        }
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Đặc Biệt", style = textStyle)
        }
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Sắp chiếu", style = textStyle)
        }
    }
}