package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.myapplication.R
import com.example.myapplication.Screen

val voucherImage = listOf(
    R.drawable.voucher_1,
    R.drawable.voucher_2,
    R.drawable.voucher_3,
    R.drawable.voucher_4,
    R.drawable.voucher_5,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar(navController)
        },
    ) { innerPadding ->
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.red_background)
                .size(Size.ORIGINAL)
                .build(),
            contentScale = ContentScale.FillBounds
        )
        val movies = homeViewModel.movies.collectAsState()
        val pageState = rememberPagerState(initialPage = 1) { movies.value.size }
        val voucherPageState = rememberPagerState(initialPage = 1) { voucherImage.size }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .paint(painter, contentScale = ContentScale.FillBounds)
                .verticalScroll(rememberScrollState())
        ) {
            VoucherCarousel(voucherPageState)
            MovieMenu()
            MovieCarousel(pageState = pageState, movies = movies.value, onMovieClick = {
                navController.navigate("${Screen.MovieDetail.route}/$it")
            })
            println("TuLN5 currentPage ${pageState.currentPage}")
            println("TuLN5 targetPage ${pageState.targetPage}")
            MovieDescription()
        }
    }
}