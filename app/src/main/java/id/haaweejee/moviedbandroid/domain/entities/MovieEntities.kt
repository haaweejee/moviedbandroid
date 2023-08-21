package id.haaweejee.moviedbandroid.domain.entities

data class MovieEntities(
    val idMovie: Int,
    val titleMovie: String,
    val posterMovie: String,
    val releaseDateMovie: String,
    val ratingMovie: Double,
    val isBookmarked: Boolean,
)
