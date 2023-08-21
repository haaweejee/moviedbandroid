package id.haaweejee.moviedbandroid.ui.component.organism.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.DetailMovieHeaderEntities
import id.haaweejee.moviedbandroid.ui.component.atom.BookmarkButton
import id.haaweejee.moviedbandroid.ui.component.atom.Rating
import id.haaweejee.moviedbandroid.ui.component.atom.RatingButton
import id.haaweejee.moviedbandroid.ui.component.organism.RatingDialog
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import id.haaweejee.moviedbandroid.ui.util.localeDateDayParseHalfMonthSecond
import id.haaweejee.moviedbandroid.ui.util.simplifyNumber

@Composable
fun DetailMovieHeader(
    modifier: Modifier = Modifier,
    navController: NavController,
    header: DetailMovieHeaderEntities,
    isRated: Boolean = false,
    isBookmark: Boolean = false,
    onBookmarkClicked: () -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }
    val rated = remember { mutableStateOf(isRated) }

    if (showDialog.value) {
        RatingDialog(
            movieId = header.idMovie,
            setShowDialog = { showDialog.value = it },
        ) { isRated ->
            rated.value = isRated
        }
    }

    Column {
        Box(modifier = modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(header.backdropMovie)
                    .crossfade(true)
                    .build(),
                alpha = 0.4f,
                contentDescription = "backdrop_path",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(175.dp)
                    .fillMaxWidth(),
            )
            Column {
                Spacer(modifier = modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 12.dp,
                            bottom = 8.dp,
                        ),
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = modifier.size(24.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = FrostedMint,
                        )
                    }
                    Row {
                        RatingButton(
                            isRated = rated.value,
                            onClick = {
                                showDialog.value = true
                            },
                        )
                        Spacer(modifier = modifier.width(6.dp))
                        BookmarkButton(
                            isBookmarked = isBookmark,
                            onBookmarkClick = onBookmarkClicked,
                        )
                    }
                }
                Spacer(modifier = modifier.height(8.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                        ),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(header.posterMovie)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = "poster_path",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .clip(CircleShape)
                            .height(75.dp)
                            .width(75.dp),
                    )
                    Column {
                        Text(
                            text = header.titleMovie,
                            fontFamily = latoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = FrostedMint,
                            modifier = modifier
                                .padding(
                                    start = 12.dp,
                                    end = 8.dp,
                                    bottom = 6.dp,
                                ),
                        )
                        Text(
                            text = header.releaseDateMovie.localeDateDayParseHalfMonthSecond(),
                            fontFamily = latoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = FrostedMint,
                            modifier = modifier
                                .padding(
                                    start = 12.dp,
                                    end = 8.dp,
                                ),
                        )
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                ),
        ) {
            Text(
                text = "Overview",
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = FrostedMint,
            )
            Rating(voteAverage = header.ratingMovie.simplifyNumber())
        }
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = header.overviewMovie,
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = FrostedMint,
            modifier = modifier
                .padding(
                    horizontal = 16.dp,
                ),
        )
    }
}
