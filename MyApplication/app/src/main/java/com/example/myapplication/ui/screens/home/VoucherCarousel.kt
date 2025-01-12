package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun VoucherCarousel(pageState: PagerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        HorizontalPager(
            state = pageState,
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 8.dp)
        ) { index ->
            CardVoucherContent(index = index, pageState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CardVoucherContent(index: Int, pagerState: PagerState) {
    val voucherPageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .clickable {
                // Click to navigate detail
            }
            .padding(4.dp)) {
        Image(
            painter = painterResource(id = voucherImage[index]),
            contentDescription = "index $index",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )


    }
}