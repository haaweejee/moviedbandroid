package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.AccountItemEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListAccountItemUseCase @Inject constructor() {

    operator fun invoke(): Flow<List<AccountItemEntities>> = flow {
        emit(
            listOf(
                AccountItemEntities(
                    icon = R.drawable.ic_bookmarked_24,
                    name = "bookmark",
                    title = "Bookmark",
                ),
                AccountItemEntities(
                    icon = R.drawable.ic_star_24,
                    name = "rated_movies_tv",
                    title = "Rated Movies/TV Series",
                ),
                AccountItemEntities(
                    icon = R.drawable.ic_information_24,
                    name = "about",
                    title = "About Apps",
                ),
            ),
        )
    }
}
