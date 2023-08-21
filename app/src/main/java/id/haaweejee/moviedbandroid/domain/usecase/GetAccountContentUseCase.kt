package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.AccountContentEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccountContentUseCase @Inject constructor(
    private val getTMDBAccountDetailUseCase: GetTMDBAccountDetailUseCase,
    private val getListAccountItemUseCase: GetListAccountItemUseCase,
) {

    suspend operator fun invoke(): Flow<AccountContentEntities> = flow {
        val header = getTMDBAccountDetailUseCase().first()
        val list = getListAccountItemUseCase().first()

        emit(AccountContentEntities(header, list))
    }
}
