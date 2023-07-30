package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import id.haaweejee.moviedbandroid.data.repository.MovieDbRepositoryImpl
import id.haaweejee.moviedbandroid.domain.entities.DetailMovieContentEntities
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
    private val repository: MovieDbRepositoryImpl,
    private val getDetailContentUseCase: GetDetailContentUseCase,
) : ViewModel() {

    private val _detailMovieState: MutableStateFlow<UiState<DetailMovieResponse>> = MutableStateFlow(UiState.Loading)
    val detailMovieState: StateFlow<UiState<DetailMovieResponse>> get() = _detailMovieState

    private val _reviewMovieState: MutableStateFlow<UiState<ReviewsResponse>> = MutableStateFlow(UiState.Loading)
    val reviewMovieState: StateFlow<UiState<ReviewsResponse>> get() = _reviewMovieState

    private val _videoMovieState: MutableStateFlow<UiState<VideoResponse>> = MutableStateFlow(UiState.Loading)
    val videoMovieState: StateFlow<UiState<VideoResponse>> get() = _videoMovieState

    private val _detailMovieContentState: MutableStateFlow<UiState<DetailMovieContentEntities>> =
        MutableStateFlow(UiState.Loading)
    val detailMovieContentState: StateFlow<UiState<DetailMovieContentEntities>> get() =
        _detailMovieContentState

    fun getDetailMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailContentUseCase(movieId).catch {
                _detailMovieContentState.value = UiState.Error(it.message.toString())
            }.collect {
                _detailMovieContentState.value = UiState.Success(it)
            }
        }
    }

    fun getDetailMovieReviews(genreId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieReview(genreId).catch {
                _reviewMovieState.value = UiState.Error(it.message.toString())
            }.collect {
                _reviewMovieState.value = UiState.Success(it)
            }
        }
    }

    fun getDetailMovieVideo(genreId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieVideo(genreId).catch {
                _videoMovieState.value = UiState.Error(it.message.toString())
            }.collect {
                _videoMovieState.value = UiState.Success(it)
            }
        }
    }
}
