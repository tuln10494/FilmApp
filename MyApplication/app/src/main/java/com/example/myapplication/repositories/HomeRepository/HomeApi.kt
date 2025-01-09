package com.example.myapplication.repositories.HomeRepository

import com.example.myapplication.models.home.NewMovieItem
import com.example.myapplication.models.home.NewsItem

interface HomeApi {
    suspend fun loadNews() : List<NewsItem>
    suspend fun loadNewMovies (): List<NewMovieItem>
    suspend fun loadSpecialNewMovies (): List<NewMovieItem>
    suspend fun loadUpComingNewMovies (): List<NewMovieItem>
}