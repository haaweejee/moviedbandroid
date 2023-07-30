package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.DetailMovieVideoEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailMovieTrailerUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String): Flow<DetailMovieVideoEntities> =
        repository.getMovieVideo(movieId).map { data ->
            var trailerKey = ""
            for (video in data.results) {
                if (video.type == "Trailer") {
                    trailerKey = video.key
                    break
                }
            }
            DetailMovieVideoEntities(
                trailerKey = trailerKey,
            )
        }
}
