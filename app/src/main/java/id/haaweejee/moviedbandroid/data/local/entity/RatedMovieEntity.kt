package id.haaweejee.moviedbandroid.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rated_movie")
data class RatedMovieEntity(
    @PrimaryKey
    val idMovie: Int? = 0,
    val userRating: Double? = 0.0,
    val isRated: Boolean? = false,
)
