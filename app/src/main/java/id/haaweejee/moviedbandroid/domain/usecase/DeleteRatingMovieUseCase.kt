package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.PostRatingEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteRatingMovieUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String): Flow<PostRatingEntities> =
        repository.deleteMovieRating(movieId).map {
            repository.deleteMovieBookmarked(movieId.toInt())
            PostRatingEntities(
                message = it.status_message,
                success = it.success,
            )
        }
}
