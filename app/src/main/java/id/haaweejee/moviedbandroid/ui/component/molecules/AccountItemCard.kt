package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun AccountItemCard(
    icon: Int,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Blumine,
        ),
        modifier = modifier
            .padding(
                horizontal = 12.dp,
                vertical = 4.dp,
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(16.dp),
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Bookmark",
                tint = FrostedMint,
            )
            Spacer(modifier = modifier.width(24.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = FrostedMint,
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
