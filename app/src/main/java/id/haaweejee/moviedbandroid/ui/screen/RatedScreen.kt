package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import id.haaweejee.moviedbandroid.ui.component.organism.rated.RatedMoviesContent
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.viewmodel.RatedViewModel

@Composable
fun RatedScreen(
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<RatedViewModel>()
    val movies = viewModel.moviesState.collectAsLazyPagingItems()

    LaunchedEffect(true) {
        viewModel.getRatedMovies()
    }
    
    Column {
        TopAppBar(
            backgroundColor = Blumine,
            title = {
                Text("Rated Movie/Tv Series", color = FrostedMint)
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
        RatedMoviesContent(
            content = movies, 
            navigateToDetail = navigateToDetail, 
            snackbarHostState = snackbarHostState,
        )
    }
}
