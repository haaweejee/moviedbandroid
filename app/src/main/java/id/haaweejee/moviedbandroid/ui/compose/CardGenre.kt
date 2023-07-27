package id.haaweejee.moviedbandroid.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.data.remote.dto.response.Genre
import id.haaweejee.moviedbandroid.ui.theme.Purple40

@Composable
fun CardGenre(
    genre: Genre,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
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
                color = Color.White,
                fontSize = 16.sp,
            )
        }
    }
}
