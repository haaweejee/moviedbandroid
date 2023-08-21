package id.haaweejee.moviedbandroid.ui.component.organism.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.domain.entities.RecommendationMovieEntities
import id.haaweejee.moviedbandroid.ui.component.atom.UnderlinedText
import id.haaweejee.moviedbandroid.ui.component.molecules.RecommendationMovieCard
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun DetailMovieRecommendationContent(
    modifier: Modifier = Modifier,
    lists: List<RecommendationMovieEntities>,
    navigateToDetail: (Int) -> Unit,
) {
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
            text = "Recommendation",
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = FrostedMint,
        )
        UnderlinedText(text = "Show More") {
        }
    }
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        items(lists) { item ->
            RecommendationMovieCard(
                data = item,
                modifier = modifier.clickable {
                    navigateToDetail(item.idMovie)
                },
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}
