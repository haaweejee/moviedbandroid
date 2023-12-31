package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.DetailMovieContentEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailContentUseCase @Inject constructor(
    private val getDetailMovieHeaderUseCase: GetDetailMovieHeaderUseCase,
    private val getDetailMovieReviewUseCase: GetDetailMovieReviewUseCase,
    private val getDetailMovieTrailerUseCase: GetDetailMovieTrailerUseCase,
    private val getMovieBookmarkedByIdUseCase: GetMovieBookmarkedByIdUseCase,
    private val getMovieRecommendationUseCase: GetMoviesRecommendationUseCase,
    private val getAccountStateUseCase: GetAccountStateUseCase,
) {

    operator fun invoke(movieId: String): Flow<DetailMovieContentEntities> = flow {
        val header = getDetailMovieHeaderUseCase(movieId).first()
        val review = getDetailMovieReviewUseCase(movieId).first()
        val trailer = getDetailMovieTrailerUseCase(movieId).first()
        val bookmarked = getMovieBookmarkedByIdUseCase(movieId.toInt()).first()
        val recommendation = getMovieRecommendationUseCase(movieId).first()
        val accountState = getAccountStateUseCase(movieId).first()
        emit(
            DetailMovieContentEntities(
                header,
                trailer,
                review,
                bookmarked == true,
                recommendation,
                accountState,
            ),
        )
    }
}
