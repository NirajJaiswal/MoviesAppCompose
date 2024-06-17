package com.niraj.moviesapp.data.network

import com.niraj.moviesapp.domain.model.Movie
import com.niraj.moviesapp.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("trending/all/week")
    suspend fun getTrendingMovies(): Response<MovieList>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieList>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en_US",
        @Query("page") page: Int = 1,
    ): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en_US",
    ): Response<Movie>

    @GET("search/movie")
    suspend fun searchMoviesByTerm(
        @Query("query") searchTerm: String,
        @Query("language") language: String = "en_US"
    ): Response<MovieList>

}