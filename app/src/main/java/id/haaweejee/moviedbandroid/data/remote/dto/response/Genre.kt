package id.haaweejee.moviedbandroid.data.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val genres: List<Genre>,
)

@Serializable
data class Genre(
    val id: Int,
    val name: String,
)
