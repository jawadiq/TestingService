package com.example.ttttt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ttttt.Api.ApiService
import com.example.ttttt.Api.Constants
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    var movieListResponse: List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("error")
    fun getMovieList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
//
//                val movieList = apiService.getMovies(Constants.CATEGORY,Constants.API_KEY,Constants.LANGUAGE,Constants.PAGE)
                val movieList = apiService.getPopularMovies(
                    Constants.API_KEY,
                    Constants.LANGUAGE,
                    Constants.PAGE
                )
                movieListResponse = movieList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}



