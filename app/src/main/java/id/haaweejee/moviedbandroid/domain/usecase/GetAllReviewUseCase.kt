package id.haaweejee.moviedbandroid.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import id.haaweejee.moviedbandroid.domain.entities.ReviewEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imagePlaceHolder
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllReviewUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String): Flow<PagingData<ReviewEntities>> =
        repository.getMovieReviewPagination(movieId).map {
            it.map { review ->
                ReviewEntities(
                    reviewId = review.id,
                    author = review.author,
                    content = review.content,
                    posterAuthor = if (review.author_details.avatar_path != null) {
                        imageUrl + review.author_details.avatar_path
                    } else {
                        imagePlaceHolder
                    },
                    contentCreated = review.created_at,
                )
            }
        }
}
