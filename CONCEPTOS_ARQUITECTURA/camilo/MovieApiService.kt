package com.example.movieapp

import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular?api_key=59b7da6c77af63f73aa792385681e6c7&language=es-ES")
    suspend fun getPopularMovies(): Response<MovieResponse>

}