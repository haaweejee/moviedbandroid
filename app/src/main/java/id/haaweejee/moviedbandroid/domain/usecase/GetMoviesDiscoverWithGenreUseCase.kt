package id.haaweejee.moviedbandroid.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import id.haaweejee.moviedbandroid.domain.entities.MovieDiscoverEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

data class GetMoviesDiscoverWithGenreUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(genreId: String): Flow<PagingData<MovieDiscoverEntities>> =
        repository.getMoviesDiscover(genreId).map {
            it.map { movie ->
                MovieDiscoverEntities(
                    idMovie = movie.id,
                    titleMovie = movie.title,
                    posterMovie = imageUrl + movie.poster_path,
                    releaseDateMovie = movie.release_date,
                    ratingMovie = movie.vote_average,
                )
            }
        }
}
