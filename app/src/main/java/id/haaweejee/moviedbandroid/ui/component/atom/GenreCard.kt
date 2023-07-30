package id.haaweejee.moviedbandroid.ui.component.atom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.domain.entities.GenreEntities
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint

@Composable
fun GenreCard(
    genre: GenreEntities,
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
            .padding(4.dp),
    ) {
        Row(
            modifier = modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = genre.name,
                color = FrostedMint,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
