package com.example.myapplication.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.navigation.NavHostController
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.Screen
import com.example.myapplication.ui.screens.home.HomeAppBar
import com.example.myapplication.ui.screens.home.HomeViewModel
import kotlin.math.absoluteValue

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
    hiltViewModel: HomeViewModel,
    mainViewModel: MainViewModel
) {
    val state = hiltViewModel.uiState.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar(navController)
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val pageState = rememberPagerState(initialPage = 1) {
                images.size
            }
            val voucherPageState = rememberPagerState(initialPage = 1) {
                voucherImage.size
            }

            Box(modifier = Modifier.height(700.dp)) {
                Image(
                    painterResource(R.drawable.red_background),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )
                Column {
                    VoucherCarousel(voucherPageState)
                    MovieMenu()
                    MovieCarousel(pageState)
                    MovieDescription()
                }
            }

//            ElevatedButton(onClick = {
//                navController.navigate(Screen.Login.route)
//            }) {
//                Text("HomeScreen")
//            }
//            Text(text = state.value.test)
//
//            ElevatedButton(onClick = {
//                hiltViewModel.updateTest("hehe")
//            }) {
//                Text("Edit String Test")
//            }
        }
    }
}

@Composable
fun MovieDescription() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                "404 Chạy Ngay Đi",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
            )
            Text(
                "2 giờ 7 phút 13 Th12 2024",
                style = TextStyle(color = Color.LightGray, fontStyle = FontStyle.Normal)
            )
        }
        FilledTonalButton(
            onClick = {
                //do something here
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        )
        {
            Text("Đặt vé")
        }
    }


}

@Composable
private fun MovieMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Đang chiếu", style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal))
        }
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Đặc Biệt", style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal))
        }
        TextButton(onClick = {
            /* do something */
        }) {
            Text("Sắp chiếu", style = TextStyle(color = Color.White, fontStyle = FontStyle.Normal))
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MovieCarousel(pageState: PagerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        HorizontalPager(
            state = pageState,
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 8.dp)
        ) { index ->
            CardMovieContent(index = index, pageState)
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun VoucherCarousel(pageState: PagerState) {
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
fun CardVoucherContent(index: Int, pagerState: PagerState) {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardMovieContent(index: Int, pagerState: PagerState) {
    val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .clickable {
                // Click to navigate detail
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
            painter = painterResource(id = images[index]),
            contentDescription = "index $index",
            contentScale = ContentScale.Crop
        )
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    // need make argument of ViewModel as Nullable to see Preview
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        hiltViewModel = HomeViewModel(null, null, null), mainViewModel = MainViewModel()
    )
}