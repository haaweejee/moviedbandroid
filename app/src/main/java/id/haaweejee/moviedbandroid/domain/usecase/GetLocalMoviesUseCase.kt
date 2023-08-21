package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLocalMoviesUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(): Flow<List<MovieEntities>> =
        repository.getMoviesBookmarked().map {
            it.map { movie ->
                MovieEntities(
                    idMovie = movie.idMovie ?: 0,
                    titleMovie = movie.titleMovie ?: "-",
                    posterMovie = movie.posterMovie ?: "-",
                    releaseDateMovie = movie.releaseDateMovie ?: "-",
                    ratingMovie = movie.ratingMovie ?: 0.0,
                    isBookmarked = movie.isBookmarked ?: false,
                )
            }
        }
}
