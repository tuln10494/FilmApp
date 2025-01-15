package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.movie.Movie

@Composable
fun OverlappingImages(movie: Movie) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxHeight(0.3f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cd_poster),
            contentDescription = "Image 1",
            modifier = Modifier.fillMaxHeight(0.8f),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = movie.name,
            modifier = Modifier.offset(x = 120.dp, y = 150.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.BottomStart)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cd_poster),
                contentDescription = "Image 2",
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.25f),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier.offset(x = 8.dp, y = 60.dp)

            ) {
                OutlinedButton(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black)
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = "")
                    Text(text = movie.releaseDate)
                }
                Row {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "", tint = Color.Red)
                    Icon(Icons.Default.Share, contentDescription = "", modifier = Modifier.offset(x = 40.dp), tint = Color.Red)
                }
            }
        }

    }
}