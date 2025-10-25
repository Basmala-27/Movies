package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.api

data class movidatamodle(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)