package com.example.myapplication.ui.screens.seats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun VoidSeatItem() {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 12.dp, height = 12.dp)
    )
}