package id.haaweejee.moviedbandroid.data.remote.dto.response.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    val genres: List<GenreResponse>? = emptyList(),
)
@Serializable
data class GenreResponse(
    val id: Int,
    val name: String,
)
