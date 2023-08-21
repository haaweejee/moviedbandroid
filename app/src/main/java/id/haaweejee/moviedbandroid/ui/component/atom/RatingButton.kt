package id.haaweejee.moviedbandroid.ui.component.atom

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint

@Composable
fun RatingButton(
    isRated: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier.size(24.dp),
    ) {
        if (isRated) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_24),
                contentDescription = "Rated",
                tint = FrostedMint,
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_border_24),
                contentDescription = "Rated",
                tint = FrostedMint,
            )
        }
    }
}
