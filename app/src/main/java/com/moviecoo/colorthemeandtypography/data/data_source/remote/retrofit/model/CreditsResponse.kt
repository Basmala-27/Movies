package com.moviecoo.colorthemeandtypography.data.data_source.remote.retrofit.model

data class CreditsResponse(
    val cast: List<CastMember>
)

data class CastMember(
    val id: Int,
    val name: String,
    val profile_path: String?
)
