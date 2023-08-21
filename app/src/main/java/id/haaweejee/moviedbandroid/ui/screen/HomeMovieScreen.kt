package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.common.UiState
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.ErrorAnimation
import id.haaweejee.moviedbandroid.ui.component.organism.home.HomeMoviesContent
import id.haaweejee.moviedbandroid.ui.component.organism.home.HomeMoviesHeader
import id.haaweejee.moviedbandroid.ui.util.ConnectionState
import id.haaweejee.moviedbandroid.ui.viewmodel.HomeViewModel
import timber.log.Timber

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    navigateToGenreMovie: (Int) -> Unit,
    networkConnectivity: ConnectionState,
    snackbarHostState: SnackbarHostState,
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    LaunchedEffect(Unit) {
        viewModel.getGenres()
        viewModel.getDiscoverMovies()
    }

    val genres by viewModel.genreState.collectAsStateWithLifecycle()
    val movies = viewModel.moviesState.collectAsLazyPagingItems()

    Column {
        if (networkConnectivity == ConnectionState.Available) {
            genres.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> Timber.d("Loading")
                    is UiState.Success -> {
                        HomeMoviesHeader(
                            title = "Welcome to MovieDbApp",
                            genres = uiState.data,
                            navigateToGenreMovie = navigateToGenreMovie,
                        )
                    }
                    is UiState.Error ->
                        CustomSnackbar(
                            message = "Data Not Available, Try Again Later",
                            snackbarHostState = snackbarHostState,
                        )
                }
            }
            HomeMoviesContent(
                content = movies,
                navigateToDetail = navigateToDetail,
                snackbarHostState = snackbarHostState,
            )
        } else {
            ErrorAnimation(
                R.raw.no_internet,
                "No Internet Connection",
                "Please Check Your Internet Connection",
            )
        }
    }
}
