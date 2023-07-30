package id.haaweejee.moviedbandroid.ui.component.organism.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.domain.entities.ReviewEntities
import id.haaweejee.moviedbandroid.ui.component.atom.UnderlinedText
import id.haaweejee.moviedbandroid.ui.component.molecules.ReviewCard
import id.haaweejee.moviedbandroid.ui.component.organism.allreview.AllReviewBottomSheet
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun DetailMovieReviewContent(
    movieId: String,
    review: List<ReviewEntities>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        AllReviewBottomSheet(
            onDismiss = {
                showSheet = false
            },
            movieId = movieId,
            snackbarHostState = snackbarHostState,
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp,
            ),
    ) {
        Text(
            text = "Reviews",
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = FrostedMint,
        )
        UnderlinedText(text = "All Review") {
            showSheet = true
        }
    }
    LazyColumn(
        modifier = modifier
            .padding(
                horizontal = 12.dp,
            ),
    ) {
        items(
            review,
        ) {
            ReviewCard(review = it)
        }
    }
}
