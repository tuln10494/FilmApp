package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardMovieContent(
    index: Int,
    pagerState: PagerState,
    imageName: String,
    onMovieClick: () -> Unit
) {
    val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

    Card(shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .clickable {
                onMovieClick()
            }
            .padding(2.dp)
            .graphicsLayer {
                lerp(
                    start = 0.85f.dp,
                    stop = 1f.dp, fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale.value
                    scaleY = scale.value
                }
                alpha = lerp(
                    start = 0.85f.dp,
                    stop = 1f.dp,
                    fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                ).value
            }) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .offset {
                    // Calculate the offset for the current page from the
                    // scroll position
                    // Then use it as a multiplier to apply an offset
                    IntOffset(
                        x = (70.dp * pageOffset).roundToPx(),
                        y = 0,
                    )
                },
            painter = painterResource(id = resourceId),
            contentDescription = "index $index",
            contentScale = ContentScale.Crop
        )
    }
}