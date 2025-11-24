package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.Result

data class MovieDataModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)