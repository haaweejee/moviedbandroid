package id.haaweejee.moviedbandroid.data.remote.dto.response.review

import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String,
)
