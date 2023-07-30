package id.haaweejee.moviedbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.haaweejee.moviedbandroid.data.repository.MovieDbRepositoryImpl
import id.haaweejee.moviedbandroid.domain.usecase.GetAllReviewUseCase
import javax.inject.Inject

@HiltViewModel
class AllReviewViewModel @Inject constructor(
    private val repositoryImpl: MovieDbRepositoryImpl,
    private val getAllReviewUseCase: GetAllReviewUseCase,
) : ViewModel() {

    fun getMovieAllReview(movieId: String) =
        getAllReviewUseCase(movieId).cachedIn(viewModelScope)
}
