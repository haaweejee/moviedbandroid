package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.ui.common.UiState
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.CustomLoading
import id.haaweejee.moviedbandroid.ui.component.molecules.ErrorAnimation
import id.haaweejee.moviedbandroid.ui.component.organism.detail.DetailMovieHeader
import id.haaweejee.moviedbandroid.ui.component.organism.detail.DetailMovieRecommendationContent
import id.haaweejee.moviedbandroid.ui.component.organism.detail.DetailMovieReviewContent
import id.haaweejee.moviedbandroid.ui.component.organism.detail.DetailMovieTrailerContent
import id.haaweejee.moviedbandroid.ui.util.ConnectionState
import id.haaweejee.moviedbandroid.ui.viewmodel.DetailMovieViewModel

@Composable
fun DetailMovieScreen(
    movieId: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    networkConnectivity: ConnectionState,
    snackbarHostState: SnackbarHostState,
    navigateToDetail: (Int) -> Unit
) {
    val viewModel = hiltViewModel<DetailMovieViewModel>()
    val detail by viewModel.detailMovieContentState.collectAsState()
    val bookmarked = viewModel.bookmarkMovieState.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        if (networkConnectivity == ConnectionState.Available) {
            bookmarked.let {
                if (it is UiState.Success) {
                    if (it.data.bookmarkStatus) {
                        CustomSnackbar(
                            message = it.data.message,
                            snackbarHostState = snackbarHostState,
                        )
                    } else {
                        CustomSnackbar(
                            message = it.data.message,
                            snackbarHostState = snackbarHostState,
                        )
                    }
                }
            }

            detail.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        CustomLoading(modifier = modifier.fillMaxSize())
                        viewModel.getDetailMovie(movieId.toString())
                    }
                    is UiState.Success -> {
                        DetailMovieHeader(
                            navController = navController,
                            header = uiState.data.header,
                            isBookmark = uiState.data.bookmark,
                            isRated = uiState.data.accountState.rated,
                            onBookmarkClicked = {
                                viewModel.insertMovieToBookmark(
                                    MovieEntities(
                                        idMovie = movieId,
                                        titleMovie = uiState.data.header.titleMovie,
                                        posterMovie = uiState.data.header.posterMovie,
                                        releaseDateMovie = uiState.data.header.releaseDateMovie,
                                        ratingMovie = uiState.data.header.ratingMovie,
                                        isBookmarked = true,
                                    ),
                                )
                            }
                        )
                        DetailMovieTrailerContent(trailer = uiState.data.trailer)
                        if (uiState.data.review.isNotEmpty()) {
                            DetailMovieReviewContent(
                                movieId = movieId.toString(),
                                review = uiState.data.review,
                                snackbarHostState = snackbarHostState,
                            )
                        }
                        if (uiState.data.recommendation.isNotEmpty()) {
                            DetailMovieRecommendationContent(
                                lists = uiState.data.recommendation,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                    is UiState.Error -> {
                        CustomSnackbar(
                            message = "Data Not Available, Try Again Later",
                            snackbarHostState = snackbarHostState,
                        )
                    }
                }
            }
        } else {
            ErrorAnimation(
                lottie = R.raw.no_internet,
                title = "No Internet Connection",
                subtitle = "Please check your internet connection",
            )
        }
    }
}
