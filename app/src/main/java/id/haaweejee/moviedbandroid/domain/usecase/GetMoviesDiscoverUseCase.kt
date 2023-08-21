package id.haaweejee.moviedbandroid.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesDiscoverUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(): Flow<PagingData<MovieEntities>> {
        return repository.getMoviesDiscover().map {
            it.map { movie ->
                MovieEntities(
                    idMovie = movie.id,
                    titleMovie = movie.title,
                    posterMovie = imageUrl + movie.poster_path,
                    releaseDateMovie = movie.release_date,
                    ratingMovie = movie.vote_average,
                    isBookmarked = false,
                )
            }
        }
    }
}
