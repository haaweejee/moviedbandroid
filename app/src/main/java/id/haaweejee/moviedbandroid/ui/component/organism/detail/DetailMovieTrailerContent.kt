package id.haaweejee.moviedbandroid.ui.component.organism.detail

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import id.haaweejee.moviedbandroid.domain.entities.DetailMovieVideoEntities
import id.haaweejee.moviedbandroid.ui.component.molecules.YoutubeCard

@Composable
fun DetailMovieTrailerContent(
    modifier: Modifier = Modifier,
    trailer: DetailMovieVideoEntities,
) {
    Spacer(modifier = modifier.height(16.dp))
    YoutubeCard(
        key = trailer.trailerKey,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
fun VideoPlayer() {
    val context = LocalContext.current
    val url = "https://is3.cloudhost.id/projectvm/animebsd.mp4"
    val exoPlayer = ExoPlayer.Builder(context).build()
    val dataSourceFactory = DefaultDataSourceFactory(LocalContext.current, "user-agent") // Replace "user-agent" with your app's user agent
    val mediaItem = MediaItem.fromUri(Uri.parse(url))
    val mediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)
    val videoSource = mediaSourceFactory.createMediaSource(mediaItem)
    exoPlayer.setMediaSource(videoSource)

    val playerView = StyledPlayerView(context)
    playerView.player = exoPlayer

    DisposableEffect(
        AndroidView(
            factory = {
                playerView.apply {
                    setFullscreenButtonClickListener {
                        // Handle full screen button click here
                        with(context) {
                            if (it) {
                                setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                            } else {
                                setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        ),
    ) {
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        onDispose {
            exoPlayer.release()
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
    if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        hideSystemUi()
    } else {
        showSystemUi()
    }
}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView,
    ).show(WindowInsetsCompat.Type.systemBars())
}
