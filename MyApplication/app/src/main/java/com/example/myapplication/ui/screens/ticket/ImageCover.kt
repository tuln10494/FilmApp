package com.example.myapplication.ui.screens.ticket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R

@Composable
fun ImageCover() {
    Image(
        painter = painterResource(id = R.drawable.cd_poster),
        contentDescription = "Image 2",
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds
    )
}