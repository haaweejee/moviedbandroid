package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.AccountStateEntities
import id.haaweejee.moviedbandroid.domain.entities.PostRatingEntities
import id.haaweejee.moviedbandroid.domain.usecase.DeleteRatingMovieUseCase
import id.haaweejee.moviedbandroid.domain.usecase.GetAccountStateUseCase
import id.haaweejee.moviedbandroid.domain.usecase.PostRatingMovieUseCase
import id.haaweejee.moviedbandroid.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val postRatingMovieUseCase: PostRatingMovieUseCase,
    private val deleteRatingMovieUseCase: DeleteRatingMovieUseCase,
    private val getAccountStateUseCase: GetAccountStateUseCase,
) : ViewModel() {

    private val _postRatingState: MutableStateFlow<PostRatingEntities> =
        MutableStateFlow(PostRatingEntities("", false))
    val postRatingState: StateFlow<PostRatingEntities> = _postRatingState

    private val _deleteRatingState: MutableStateFlow<PostRatingEntities> =
        MutableStateFlow(PostRatingEntities("", false))
    val deleteRatingState: StateFlow<PostRatingEntities> get() = _deleteRatingState

    private val _accountState: MutableStateFlow<UiState<AccountStateEntities>> = MutableStateFlow(
        UiState.Loading,
    )
    val accountState: StateFlow<UiState<AccountStateEntities>>
        get() =
            _accountState

    fun postRatingMovie(movieId: String, rating: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            postRatingMovieUseCase(movieId, rating).collect {
                _postRatingState.value = it
            }
        }
    }

    fun deleteRatingMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRatingMovieUseCase(movieId).collect {
                _deleteRatingState.value = it
            }
        }
    }

    fun getAccountState(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountStateUseCase(movieId).catch {
                _accountState.value = UiState.Error(it.message.toString())
            }.collect {
                _accountState.value = UiState.Success(it)
            }
        }
    }
}
