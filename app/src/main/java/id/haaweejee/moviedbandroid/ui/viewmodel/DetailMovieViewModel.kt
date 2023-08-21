package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.BookmarkEntities
import id.haaweejee.moviedbandroid.domain.entities.DetailMovieContentEntities
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.usecase.BookmarkMovieUseCase
import id.haaweejee.moviedbandroid.domain.usecase.GetDetailContentUseCase
import id.haaweejee.moviedbandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailContentUseCase: GetDetailContentUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
) : ViewModel() {

    private val _detailMovieContentState: MutableStateFlow<UiState<DetailMovieContentEntities>> =
        MutableStateFlow(UiState.Loading)
    val detailMovieContentState: StateFlow<UiState<DetailMovieContentEntities>>
        get() =
            _detailMovieContentState

    private val _bookmarkMovieState: MutableStateFlow<UiState<BookmarkEntities>> = MutableStateFlow(UiState.Loading)
    val bookmarkMovieState: StateFlow<UiState<BookmarkEntities>>
        get() =
            _bookmarkMovieState

    fun getDetailMovie(movieId: String) {
        viewModelScope.launch {
            getDetailContentUseCase(movieId).catch {
                _detailMovieContentState.value = UiState.Error(it.message.toString())
            }.collect {
                _detailMovieContentState.value = UiState.Success(it)
            }
        }
    }

    fun insertMovieToBookmark(movie: MovieEntities) =
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkMovieUseCase(movie).catch {
                _bookmarkMovieState.value = UiState.Error(it.message.toString())
            }.collect {
                _bookmarkMovieState.value = UiState.Success(it)
            }
        }
}
