package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.data.local.entity.RatedMovieEntity
import id.haaweejee.moviedbandroid.domain.entities.PostRatingEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRatingMovieUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(movieId: String, rating: Double): Flow<PostRatingEntities> =
        repository.postMovieRating(movieId, rating).map {
            repository.insertRatedMovie(
                RatedMovieEntity(
                    idMovie = movieId.toInt(),
                    userRating = rating,
                    isRated = true,
                ),
            )
            PostRatingEntities(
                message = it.status_message,
                success = it.success,
            )
        }
}
