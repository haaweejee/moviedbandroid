package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities
import id.haaweejee.moviedbandroid.ui.component.atom.Rating
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import id.haaweejee.moviedbandroid.ui.util.localeDateDayParseHalfMonthSecond

@Composable
fun MovieCard(
    data: MovieEntities,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Blumine,
        ),
        modifier = modifier
            .padding(bottom = 12.dp),
    ) {
        Column(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.large),
        ) {
            AsyncImage(
                model = data.posterMovie,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = data.posterMovie,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
            Text(
                text = data.titleMovie,
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = FrostedMint,
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        start = 12.dp,
                        end = 8.dp,
                        bottom = 4.dp,
                    ),
            )
            Text(
                text = "Released Date: ${data.releaseDateMovie.localeDateDayParseHalfMonthSecond()}",
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = FrostedMint,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
            )
            Rating(voteAverage = data.ratingMovie)
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}
