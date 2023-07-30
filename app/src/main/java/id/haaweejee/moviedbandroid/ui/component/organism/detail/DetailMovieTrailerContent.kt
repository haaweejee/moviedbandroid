package id.haaweejee.moviedbandroid.ui.component.organism.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.haaweejee.moviedbandroid.domain.entities.DetailMovieVideoEntities
import id.haaweejee.moviedbandroid.ui.component.molecules.YoutubeCard

@Composable
fun DetailMovieTrailerContent(
    modifier: Modifier = Modifier,
    trailer: DetailMovieVideoEntities
) {
    Spacer(modifier = modifier.height(24.dp))
    YoutubeCard(
        key = trailer.trailerKey,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
            ),
    )
}