package com.example.myapplication.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.movie.Movie

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MovieCarousel(
    pageState: PagerState,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        HorizontalPager(
            state = pageState,
            contentPadding = PaddingValues(horizontal = 60.dp, vertical = 8.dp)
        ) { index ->
            CardMovieContent(
                index = index,
                pagerState = pageState,
                movie = movies[index],
                onMovieClick = onMovieClick,
            )
        }
    }
}