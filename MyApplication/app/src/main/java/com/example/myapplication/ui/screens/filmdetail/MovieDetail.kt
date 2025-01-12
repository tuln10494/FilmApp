package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenMovieDetail(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OverlappingImages()
        Spacer(modifier = Modifier.height(2.dp))
        SeeMoreTextExample()
        Spacer(modifier = Modifier.height(2.dp))
        InfoDetailText()
        Spacer(modifier = Modifier.height(2.dp))
        BookTicketButton(navController)
    }
}











