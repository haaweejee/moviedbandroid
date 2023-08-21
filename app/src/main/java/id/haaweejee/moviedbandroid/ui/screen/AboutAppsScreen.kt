package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.haaweejee.moviedbandroid.BuildConfig
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun AboutAppsScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    navController: NavController,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            backgroundColor = Blumine,
            title = {
                Text("About Apps", color = FrostedMint)
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = FrostedMint,
                    )
                }
            },
        )
        Spacer(modifier = modifier.height(16.dp))
        Column {
            Image(
                painter = painterResource(id = R.drawable.movie_db),
                contentDescription = "logo",
                modifier = modifier
                    .width(200.dp)
                    .height(200.dp),
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Movie DB Version: ${BuildConfig.VERSION_NAME}",
            color = FrostedMint,
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Movie db is an application that is useful for displaying a list of films, apart from that this application is used for application development training.",
            color = FrostedMint,
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 16.dp),
        )
    }
}
