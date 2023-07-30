package id.haaweejee.moviedbandroid.domain.usecase

import id.haaweejee.moviedbandroid.domain.entities.GenreEntities
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val repository: MovieDbRepository,
) {

    operator fun invoke(): Flow<List<GenreEntities>> = repository.getGenres().map {
        it.genres?.map { genre ->
            GenreEntities(
                id = genre.id,
                name = genre.name,
            )
        } ?: emptyList()
    }
}
