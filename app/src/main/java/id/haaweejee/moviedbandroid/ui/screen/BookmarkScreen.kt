package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.component.molecules.ErrorAnimation
import id.haaweejee.moviedbandroid.ui.component.molecules.MovieCard
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.viewmodel.BookmarkViewModel

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    val viewModel = hiltViewModel<BookmarkViewModel>()
    val localMovies = viewModel.localMovies.collectAsState().value
    viewModel.getLocalMovies()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TopAppBar(
            backgroundColor = Blumine,
            title = {
                Text("Bookmark", color = FrostedMint)
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = FrostedMint,
                    )
                }
            },
        )
        Spacer(modifier = modifier.height(16.dp))
        if (localMovies.isEmpty()) {
            ErrorAnimation(
                lottie = R.raw.empty_list,
                title = "Bookmarked Empty",
                subtitle = "Please bookmark your favorite movie/tv series.",
            )
        } else {
            LazyColumn(
                modifier.padding(
                    horizontal = 12.dp,
                ),
            ) {
                items(localMovies) { movie ->
                    MovieCard(
                        data = movie,
                        modifier = modifier.clickable {
                            navigateToDetail(movie.idMovie)
                        },
                    )
                }
            }
        }
    }
}
