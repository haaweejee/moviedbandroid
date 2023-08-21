package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieBookmarkedByIdUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(id: Int): Flow<Boolean?> =
        repository.getMovieBookmarkById(id).map {
            if (it != null) {
                it.isBookmarked
            } else {
                false
            }
        }
}
