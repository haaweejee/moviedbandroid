package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.mapper.toMovieEntity
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import javax.inject.Inject

class InsertMovieToBookmarkUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    suspend operator fun invoke(movie: MovieEntities) {
        repository.insertMovieToBookmark(
            movie.toMovieEntity(),
        )
    }
}
