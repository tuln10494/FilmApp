package com.example.myapplication.ui.screens.filmdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

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