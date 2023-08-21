package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.AccountDetailEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import id.haaweejee.moviedbandroid.domain.util.imageUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTMDBAccountDetailUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(): Flow<AccountDetailEntities> =
        repository.getAccountDetail().map {
            AccountDetailEntities(
                avatarImage = imageUrl + it.avatar.tmdb.avatar_path,
                userName = it.username,
                name = it.name,
            )
        }
}