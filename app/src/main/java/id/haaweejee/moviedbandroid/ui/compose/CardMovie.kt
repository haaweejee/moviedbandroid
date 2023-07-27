package id.haaweejee.moviedbandroid.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import id.haaweejee.moviedbandroid.data.remote.dto.response.Result
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CardMovie(
    data: Result,
    modifier: Modifier = Modifier,
) {
    val imageUrl = "https://image.tmdb.org/t/p/w500"

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        modifier = modifier
            .padding(bottom = 12.dp),
    ) {
        Column(
            modifier = modifier
                .clip(shape = MaterialTheme.shapes.large),
        ) {
            AsyncImage(
                model = imageUrl + data.poster_path,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentDescription = data.poster_path,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
            Text(
                text = data.title,
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 4.dp,
                    ),
            )
            Text(
                text = "Released Date: ${localeDateDayParseHalfMonthSecond(data.release_date)}",
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
            )
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}

val dateOnly = SimpleDateFormat("yyyy-MM-dd", Locale.US)
val dateDayWithHalfMonthSecond = SimpleDateFormat("dd MMM yyyy", Locale.US)

fun localeDateDayParseHalfMonthSecond(time: String): String {
    return try {
        val date = dateOnly.parse(time)
        dateDayWithHalfMonthSecond.format(date!!)
    } catch (ex: Exception) {
        ""
    }
}
