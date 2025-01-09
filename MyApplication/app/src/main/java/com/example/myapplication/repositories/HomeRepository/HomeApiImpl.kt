package com.example.myapplication.repositories.HomeRepository

import com.example.myapplication.models.home.NewMovieItem
import com.example.myapplication.models.home.NewsItem

class HomeApiImpl : HomeApi {
    override suspend fun loadNews(): List<NewsItem> {
        TODO("Not yet implemented")
    }

    override suspend fun loadNewMovies(): List<NewMovieItem> {
        TODO("Not yet implemented")
    }

    override suspend fun loadSpecialNewMovies(): List<NewMovieItem> {
        TODO("Not yet implemented")
    }

    override suspend fun loadUpComingNewMovies(): List<NewMovieItem> {
        TODO("Not yet implemented")
    }
}