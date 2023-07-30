package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.usecase.GetMoviesDiscoverWithGenreUseCase
import javax.inject.Inject

@HiltViewModel
class GenreMovieViewModel @Inject constructor(
    private val getMoviesDiscoverWithGenreUseCase: GetMoviesDiscoverWithGenreUseCase,
) : ViewModel() {

    fun getDiscoverMovies(genreId: String) =
        getMoviesDiscoverWithGenreUseCase(genreId).cachedIn(viewModelScope)
}
