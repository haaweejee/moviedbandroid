package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.domain.usecase.GetLocalMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
) : ViewModel() {

    private val _localMovies: MutableStateFlow<List<MovieEntities>> = MutableStateFlow(listOf())
    val localMovies: StateFlow<List<MovieEntities>> get() = _localMovies

    fun getLocalMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocalMoviesUseCase().collect {
                _localMovies.value = it
            }
        }
    }
}
