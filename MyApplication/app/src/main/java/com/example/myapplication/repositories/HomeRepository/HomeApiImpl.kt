package com.example.myapplication.repositories.HomeRepository

import com.example.myapplication.models.home.NewMovieItem
import com.example.myapplication.models.home.NewsItem
import javax.inject.Inject

class HomeApiImpl @Inject constructor() : HomeApi {

    var news = ArrayList<NewsItem>()
    var newMovies = ArrayList<NewMovieItem>()
    var specialMovies = ArrayList<NewMovieItem>()
    var upComingNewMovies = ArrayList<NewMovieItem>()

    override suspend fun loadNews(): List<NewsItem> {
        return news
    }

    override suspend fun loadNewMovies(): List<NewMovieItem> {
        return newMovies
    }

    override suspend fun loadSpecialNewMovies(): List<NewMovieItem> {
        return specialMovies
    }

    override suspend fun loadUpComingNewMovies(): List<NewMovieItem> {
        return upComingNewMovies
    }
}