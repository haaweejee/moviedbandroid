package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.usecase.GetRatedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatedViewModel @Inject constructor(
    private val getRatedMoviesUseCase: GetRatedMoviesUseCase,
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<MovieEntities>> = MutableStateFlow(
        PagingData.empty(),
    )
    val moviesState: StateFlow<PagingData<MovieEntities>> get() = _moviesState

    fun getRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getRatedMoviesUseCase().collect {
                _moviesState.value = it
            }
        }
    }
}
