package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.DetailMovieHeaderEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailMovieHeaderUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(movieId: String): Flow<DetailMovieHeaderEntities> =
        repository.getMovieDetail(movieId).map {
            DetailMovieHeaderEntities(
                idMovie = it.id ?: 0,
                titleMovie = it.title.orEmpty(),
                posterMovie = imageUrl + it.poster_path,
                backdropMovie = imageUrl + it.backdrop_path,
                releaseDateMovie = it.release_date.orEmpty(),
                ratingMovie = it.vote_average ?: 0.0,
                overviewMovie = it.overview.orEmpty(),
            )
        }
}
