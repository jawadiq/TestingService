package com.example.ttttt.test


import com.example.ttttt.test.Result
import com.google.gson.annotations.SerializedName

data class Main(
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)