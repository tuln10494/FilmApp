package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

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
        PreviewVerticalImageList()
        Spacer(modifier = Modifier.height(2.dp))
        BookTicketButton(navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun OverlappingImages() {
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
            text = "Movie Title",
            modifier = Modifier.offset(x = 120.dp, y = 150.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
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
                    Text(
                        text = "11-12-2024"
                    )
                }
                Row {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "", tint = Color.Red)
                    Icon(Icons.Default.Share, contentDescription = "", modifier = Modifier.offset(x = 40.dp), tint = Color.Red)
                }
            }

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
                    Text(
                        text = "11-12-2024"
                    )
                }

            }
        }

    }
}

@Composable
fun SeeMoreTextExample() {
    Text(
        text = "Trò chơi con mực là một bộ phim truyền hình Hàn Quốc do Hwang Dong-hyuk viết kịch bản và đạo diễn. " +
                "Phim được Netflix phát hành trực tuyến vào ngày 17 tháng 9 năm 2021. " +
                "Phim kể về một nhóm 456 người mạo hiểm tính mạng trong 6 trò chơi",
        modifier = Modifier.padding(start = 8.dp).fillMaxHeight(0.2f)
    )
}

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

@Composable
fun VerticalImageList(imageIds: List<Int>) {
    LazyRow(
        modifier = Modifier.fillMaxHeight(0.5f)
    ) {
        items(imageIds.size) { index ->
            // Each item is wrapped in a Card with padding
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(1f)
                    .padding(8.dp),
                shape = RoundedCornerShape(CornerSize(16.dp)),
                //elevation = 4.dp
            ) {
                // Image for each item
                Image(
                    painter = painterResource(id = imageIds[index]),
                    contentDescription = "Image $index",
                    modifier = Modifier.fillMaxWidth(0.5f),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewVerticalImageList() {
    // Provide a list of drawable resource IDs
    VerticalImageList(
        imageIds = listOf(
            R.drawable.cd_poster,
            R.drawable.cd_poster,
            R.drawable.cd_poster,
            R.drawable.cd_poster
        )
    )
}

@Composable
fun BookTicketButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Fills the entire screens or parent
            .padding(8.dp), // Optional padding for spacing
        verticalArrangement = Arrangement.Center, // Centers vertically
        horizontalAlignment = Alignment.CenterHorizontally // Centers horizontally
    ) {
        OutlinedButton(onClick = {
            //navController.navigate(Screen.Ticket.route)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Color.Red
            )) {
            Text(text = "Đặt Vé")
        }
    }
}