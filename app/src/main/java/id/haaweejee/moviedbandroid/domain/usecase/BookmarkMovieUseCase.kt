package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.BookmarkEntities
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkMovieUseCase @Inject constructor(
    val insert: InsertMovieToBookmarkUseCase,
    val delete: DeleteMovieBookmarkedUseCase,
    val get: GetMovieBookmarkedByIdUseCase,
) {

    suspend operator fun invoke(
        data: MovieEntities,
    ): Flow<BookmarkEntities> {
        val bookmarked = get(data.idMovie).first()
        return if (bookmarked == true) {
            delete(data.idMovie)
            flow {
                emit(BookmarkEntities("Unbookmark Movies",false))
            }
        } else {
            insert(data)
            flow {
                emit(BookmarkEntities("Bookmark Movies",true))
            }
        }
    }
}
