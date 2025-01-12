package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InfoDetailText() {
    Column (
        modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(0.25f)
    ) {
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Kiem duyet", fontWeight = FontWeight.Bold)
            Text(text = "Phim duoc phep chieu", modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "The loai", fontWeight = FontWeight.Bold)
            Text(text = "Hanh dong", modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Dao dien", fontWeight = FontWeight.Bold)
            Text(text = "Jacky Chan", modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Dien vien", fontWeight = FontWeight.Bold)
            Text(text = "Tony Jae", modifier = Modifier.offset(x = 8.dp))
        }
        Row (
            modifier = Modifier.offset(x = 8.dp)
        ) {
            Text(text = "Ngon ngu", fontWeight = FontWeight.Bold)
            Text(text = "Tieng Viet", modifier = Modifier.offset(x = 8.dp))
        }
    }
}