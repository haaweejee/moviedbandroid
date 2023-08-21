package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.GenreEntities
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.usecase.GetGenreListUseCase
import id.haaweejee.moviedbandroid.domain.usecase.GetMoviesDiscoverUseCase
import id.haaweejee.moviedbandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenreListUseCase: GetGenreListUseCase,
    private val getMoviesDiscoverUseCase: GetMoviesDiscoverUseCase,
) : ViewModel() {

    private val _genreState: MutableStateFlow<UiState<List<GenreEntities>>> = MutableStateFlow(UiState.Loading)
    val genreState: StateFlow<UiState<List<GenreEntities>>> get() = _genreState

    private val _moviesState: MutableStateFlow<PagingData<MovieEntities>> = MutableStateFlow(PagingData.empty())
    val moviesState: StateFlow<PagingData<MovieEntities>> get() = _moviesState

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            getGenreListUseCase().catch {
                _genreState.value = UiState.Error(it.message.toString())
            }.collect {
                _genreState.value = UiState.Success(it)
            }
        }
    }

    fun getDiscoverMovies() =
        viewModelScope.launch(Dispatchers.IO) {
            getMoviesDiscoverUseCase()
                .catch {
                    _moviesState.value = PagingData.empty()
                }.cachedIn(viewModelScope).collect {
                    _moviesState.value = it
                }
        }
}
