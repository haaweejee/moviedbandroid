package id.haaweejee.moviedbandroid.data.remote.dto.response.review

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetails(
    val avatar_path: String?,
    val name: String,
    val rating: Int,
    val username: String,
)
