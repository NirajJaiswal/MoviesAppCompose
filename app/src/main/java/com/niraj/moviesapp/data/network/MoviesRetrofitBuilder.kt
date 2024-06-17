package com.niraj.moviesapp.data.network

import com.niraj.moviesapp.BuildConfig
import com.niraj.moviesapp.util.MoviesConstant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRetrofitBuilder {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: MoviesApiService = getRetrofit().create(MoviesApiService::class.java)

}