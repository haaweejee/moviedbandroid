package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.RecommendationMovieEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imagePlaceHolder
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesRecommendationUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {
    operator fun invoke(movieId: String): Flow<List<RecommendationMovieEntities>> =
        repository.getMovieRecommendation(movieId).map {
            it.results.map { movie ->
                RecommendationMovieEntities(
                    idMovie = movie.id,
                    titleMovie = movie.title,
                    posterMovie = if (movie.poster_path != null) {
                        imageUrl + movie.poster_path
                    } else {
                        imagePlaceHolder
                    },
                    ratingMovie = movie.vote_average,
                )
            }.take(7)
        }
}
