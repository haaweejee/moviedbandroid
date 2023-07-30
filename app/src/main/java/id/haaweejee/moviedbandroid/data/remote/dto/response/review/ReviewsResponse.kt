package id.haaweejee.moviedbandroid.data.remote.dto.response.review

import kotlinx.serialization.Serializable

@Serializable
data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<ReviewResponse>,
    val total_pages: Int,
    val total_results: Int,
)
