package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.ReviewEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class GetDetailMovieReviewUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String): Flow<List<ReviewEntities>> =
        repository.getMovieReview(movieId).map {
            it.results.map { review ->
                ReviewEntities(
                    reviewId = review.id,
                    author = review.author,
                    content = review.content,
                    posterAuthor = imageUrl + review.author_details.avatar_path,
                    contentCreated = review.created_at,
                )
            }.take(3)
        }
}
