package id.haaweejee.moviedbandroid.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class LocalMovieEntity(
    @PrimaryKey
    val idMovie: Int? = 0,
    val titleMovie: String? = "",
    val posterMovie: String? = "",
    val releaseDateMovie: String? = "",
    val ratingMovie: Double? = 0.0,
    val isBookmarked: Boolean? = false,
)
