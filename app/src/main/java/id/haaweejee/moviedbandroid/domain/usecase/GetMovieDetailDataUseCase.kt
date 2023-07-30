package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import javax.inject.Inject

class GetMovieDetailDataUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String) = repository.getMovieDetail(movieId)
}