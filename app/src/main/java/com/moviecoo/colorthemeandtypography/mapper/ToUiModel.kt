package com.moviecoo.colorthemeandtypography.mapper

import com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model.CastMember
import com.moviecoo.colorthemeandtypography.ui.Screens.detailsScreen.data.CastUiModel

fun CastMember.toUiModel(): CastUiModel {
    return CastUiModel(
        name = name,
        imageUrl = profile_path?.let { "https://image.tmdb.org/t/p/w200$it" }
    )
}
