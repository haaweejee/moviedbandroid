package id.haaweejee.moviedbandroid.ui.component.atom

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint

@Composable
fun BookmarkButton(
    isBookmarked: Boolean = false,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var bookmark by remember { mutableStateOf(isBookmarked) }

    IconButton(
        onClick = {
            bookmark = !bookmark
            onBookmarkClick()
        },
        modifier = modifier.size(24.dp),
    ) {
        if (bookmark) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmarked_24),
                contentDescription = "bookmark",
                tint = FrostedMint,
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_bookmark_24),
                contentDescription = "bookmark",
                tint = FrostedMint,
            )
        }
    }
}
