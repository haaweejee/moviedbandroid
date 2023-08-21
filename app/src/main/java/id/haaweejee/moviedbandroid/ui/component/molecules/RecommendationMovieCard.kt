package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.RecommendationMovieEntities
import id.haaweejee.moviedbandroid.ui.component.atom.Rating
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import id.haaweejee.moviedbandroid.ui.util.simplifyNumber

@Composable
fun RecommendationMovieCard(
    modifier: Modifier = Modifier,
    data: RecommendationMovieEntities,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Blumine,
        ),
        modifier = modifier
            .width(100.dp)
            .height(240.dp)
            .padding(
                end = 8.dp,
                bottom = 8.dp,
            ),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.fillMaxHeight(),
        ) {
            Column {
                AsyncImage(
                    model = data.posterMovie,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentDescription = data.posterMovie,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(100.dp)
                        .height(150.dp),
                )
                Spacer(modifier = modifier.height(2.dp))
                Text(
                    text = data.titleMovie,
                    fontFamily = latoFontFamily,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    color = FrostedMint,
                    modifier = modifier
                        .padding(
                            top = 8.dp,
                            start = 4.dp,
                            end = 4.dp,
                            bottom = 4.dp,
                        ),
                )
            }
            Rating(
                voteAverage = data.ratingMovie.simplifyNumber(),
                modifier = modifier.padding(
                    bottom = 4.dp,
                ),
            )
        }
    }
}
