package com.example.ttttt.Api


import com.example.ttttt.GetMoviesResponses
import com.example.ttttt.Movie

import com.example.ttttt.test.Result
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {




    @GET("c3253d16-4da7-46ee-b385-d8cf58aa7b71")
    suspend fun getAllMovies(): List<Movie>




    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "78974d98b3b8a5424924fc0385c7bc00",
        @Query("language") language: String,
        @Query("page") page: Int
    ): List<Movie>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}

