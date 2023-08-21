package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.domain.entities.GenreEntities
import id.haaweejee.moviedbandroid.ui.component.atom.GenreCard
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.MidNight
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandableGenreCard(
    genres: List<GenreEntities> = emptyList(),
    navigateToGenreMovie: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MidNight,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                expanded = !expanded
            },
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Genre List",
                    color = FrostedMint,
                    fontSize = 20.sp,
                    fontFamily = latoFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (!expanded) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand_more_36),
                        contentDescription = "arrow forward",
                        tint = FrostedMint,
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand_less_36),
                        contentDescription = "arrow down",
                        tint = FrostedMint,
                    )
                }
            }
            if (expanded) {
                FlowRow {
                    genres.forEach {
                        GenreCard(
                            genre = it,
                            modifier = modifier.clickable {
                                navigateToGenreMovie(it.id)
                            },
                        )
                    }
                }
            }
        }
    }
}
