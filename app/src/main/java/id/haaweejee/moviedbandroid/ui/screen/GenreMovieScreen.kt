package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.component.molecules.ErrorAnimation
import id.haaweejee.moviedbandroid.ui.component.organism.genre.GenreMoviesContent
import id.haaweejee.moviedbandroid.ui.component.organism.genre.GenreMoviesHeader
import id.haaweejee.moviedbandroid.ui.util.ConnectionState
import id.haaweejee.moviedbandroid.ui.viewmodel.GenreMovieViewModel

@Composable
fun GenreMoviesScreen(
    genreId: Int,
    navigateToDetail: (Int) -> Unit,
    navController: NavHostController,
    networkConnectivity: ConnectionState,
    snackbarHostState: SnackbarHostState,
) {
    val viewModel = hiltViewModel<GenreMovieViewModel>()
    val movies = viewModel.getDiscoverMovies(genreId.toString()).collectAsLazyPagingItems()

    Column {
        GenreMoviesHeader(
            genreId = genreId,
            navController = navController,
        )
        if (networkConnectivity == ConnectionState.Available) {
            GenreMoviesContent(
                movies = movies,
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
