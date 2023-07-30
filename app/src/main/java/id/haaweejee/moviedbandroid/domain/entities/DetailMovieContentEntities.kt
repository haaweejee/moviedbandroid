package id.haaweejee.moviedbandroid.domain.entities

data class DetailMovieContentEntities(
    val header: DetailMovieHeaderEntities,
    val trailer: DetailMovieVideoEntities,
    val review: List<ReviewEntities>,
)
