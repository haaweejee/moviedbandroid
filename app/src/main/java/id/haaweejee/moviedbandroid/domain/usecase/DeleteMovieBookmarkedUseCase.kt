package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import javax.inject.Inject

class DeleteMovieBookmarkedUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    suspend operator fun invoke(movieId: Int) {
        repository.deleteMovieBookmarked(
            movieId,
        )
    }
}