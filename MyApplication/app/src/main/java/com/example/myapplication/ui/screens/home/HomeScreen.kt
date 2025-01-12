package com.example.myapplication.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.ui.screens.home.HomeAppBar
import com.example.myapplication.ui.screens.home.HomeViewModel
import com.example.myapplication.ui.screens.home.MovieCarousel
import com.example.myapplication.ui.screens.home.MovieDescription
import com.example.myapplication.ui.screens.home.MovieMenu
import com.example.myapplication.ui.screens.home.VoucherCarousel

val images = listOf(
    R.drawable.cd_poster,
    R.drawable.run_now_poster,
    R.drawable.spirited_away_poster,
    R.drawable.sth3_poster,
    R.drawable.whisper_of_the_heart_poster,
)

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
    homeViewModel: HomeViewModel,
    mainViewModel: MainViewModel
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
        val pageState = rememberPagerState(initialPage = 1) { images.size }
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
            MovieCarousel(pageState)
            MovieDescription()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    // need make argument of ViewModel as Nullable to see Preview
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        homeViewModel = HomeViewModel(null, null, null), mainViewModel = MainViewModel()
    )
}