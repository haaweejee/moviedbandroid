package id.haaweejee.moviedbandroid.data.remote.dto.response.movielist

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int,
)
