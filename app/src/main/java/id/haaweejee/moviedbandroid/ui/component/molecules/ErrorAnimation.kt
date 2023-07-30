@file:Suppress("DEPRECATION")

package id.haaweejee.moviedbandroid.ui.component.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun ErrorAnimation(
    lottie: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    val isPlaying by remember {
        mutableStateOf(true)
    }
    val speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(lottie),
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false,
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(400.dp),
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Bold,
            color = FrostedMint,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = subtitle,
            fontSize = 16.sp,
            fontFamily = latoFontFamily,
            fontWeight = FontWeight.Normal,
            color = FrostedMint,
        )
    }
}
