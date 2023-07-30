package id.haaweejee.moviedbandroid.ui.component.organism.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.domain.entities.GenreEntities
import id.haaweejee.moviedbandroid.ui.component.molecules.ExpandableGenreCard
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun HomeMoviesHeader(
    title: String,
    navigateToGenreMovie: (Int) -> Unit,
    genres: List<GenreEntities>,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        modifier = modifier.padding(
            start = 12.dp,
            end = 12.dp,
            top = 16.dp,
            bottom = 4.dp,
        ),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = latoFontFamily,
        color = FrostedMint,
    )
    if (genres.isNotEmpty()) {
        ExpandableGenreCard(
            genres = genres,
            navigateToGenreMovie = navigateToGenreMovie,
        )
    } else {
        Spacer(modifier = modifier.height(16.dp))
    }
}
