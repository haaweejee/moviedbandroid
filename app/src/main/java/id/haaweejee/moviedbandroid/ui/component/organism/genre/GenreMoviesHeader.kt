package id.haaweejee.moviedbandroid.ui.component.organism.genre

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.util.toGenreName

@Composable
fun GenreMoviesHeader(
    genreId: Int,
    navController: NavController,
) {
    TopAppBar(
        backgroundColor = Blumine,
        title = {
            Text(genreId.toGenreName(), color = FrostedMint)
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
}
