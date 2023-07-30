package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.common.UiState
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.CustomLoading
import id.haaweejee.moviedbandroid.ui.component.molecules.ErrorAnimation
import id.haaweejee.moviedbandroid.ui.component.organism.detail.DetailMovieHeader
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
) {
    val viewModel = hiltViewModel<DetailMovieViewModel>()

    Column(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .fillMaxSize(),
    ) {
        if (networkConnectivity == ConnectionState.Available) {
            viewModel.detailMovieContentState.collectAsState(initial = UiState.Loading).value.let {
                    uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        CustomLoading(modifier = modifier.fillMaxSize())
                        viewModel.getDetailMovie(movieId.toString())
                    }
                    is UiState.Success -> {
                        DetailMovieHeader(
                            navController = navController,
                            header = uiState.data.header,
                        )
                        DetailMovieTrailerContent(trailer = uiState.data.trailer)
                        if (uiState.data.review.isNotEmpty()) {
                            DetailMovieReviewContent(
                                movieId = movieId.toString(),
                                review = uiState.data.review,
                                snackbarHostState = snackbarHostState,
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
