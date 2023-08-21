package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.AccountStateEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAccountStateUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(movieId: String): Flow<AccountStateEntities> =
        repository.getAccountState(movieId).map {
            val localRated = repository.getRatedMovieById(movieId.toInt()).first()
            val isRatedMovie = if (localRated != null) {
                localRated.isRated
            } else {
                false
            }
            AccountStateEntities(
                favorite = it.favorite,
                rated = it.rated !is Boolean,
                watchlist = it.watchlist,
            )
        }
}
