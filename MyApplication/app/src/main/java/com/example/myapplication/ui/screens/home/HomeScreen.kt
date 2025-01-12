package com.example.myapplication.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.navigation.NavHostController
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.ui.screens.home.HomeAppBar
import kotlin.math.absoluteValue

val images = listOf(
    R.drawable.cd_poster,
    R.drawable.run_now_poster,
    R.drawable.spirited_away_poster,
    R.drawable.sth3_poster,
    R.drawable.whisper_of_the_heart_poster,
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
            HomeAppBar()
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val pageState = rememberPagerState(initialPage = 1) {
                images.size
            }
            HorizontalPager(state = pageState, contentPadding = PaddingValues(50.dp)) { index ->
                CardMovieContent(index = index, pageState)
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardMovieContent(index: Int, pagerState: PagerState) {
    val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
    Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.clickable {
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
                start = 0.5f.dp,
                stop = 1f.dp,
                fraction = 1f -pageOffset.absoluteValue.coerceIn(0f,1f)
            ).value
        }) {
        Image(
            modifier = Modifier.fillMaxSize(),
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