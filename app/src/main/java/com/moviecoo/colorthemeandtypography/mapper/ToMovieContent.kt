package com.moviecoo.colorthemeandtypography.mapper

// MovieContentDto.kt
data class MovieContentDto(
    val id: Int,
    val title: String,
    val overview: String,
    val videos: VideoResponse?
)

data class VideoResponse(
    val results: List<VideoDto>
)

data class VideoDto(
    val id: String,     // <-- ADDED: The unique ID of the video object
    val name: String,   // <-- ADDED: The title of the video (e.g., "Official Trailer 1")
    val key: String,    // YouTube key
    val site: String,   // "YouTube"
    val type: String    // "Trailer" / "Clip"
)

// تحويل DTO لـ UI model
data class MovieContent(
    val id: Int,
    val title: String,
    val overview: String,
    val videoUrl: String
)

fun MovieContentDto.toMovieContent(): MovieContent {
    val trailer = videos?.results?.firstOrNull { it.type == "Trailer" && it.site == "YouTube" }
    val url = trailer?.key?.let { "https://www.youtube.com/watch?v=$it" } ?: ""
    return MovieContent(
        id = id,
        title = title,
        overview = overview,
        videoUrl = url
    )
}
