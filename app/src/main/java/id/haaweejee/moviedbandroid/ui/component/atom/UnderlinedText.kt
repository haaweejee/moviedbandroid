package id.haaweejee.moviedbandroid.ui.component.atom

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily

@Composable
fun UnderlinedText(text: String, onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(textDecoration = TextDecoration.Underline),
            start = 0,
            end = text.length,
        )
    }

    Text(
        text = annotatedString,
        fontFamily = latoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = FrostedMint,
        modifier = Modifier.clickable { onClick() },
    )
}
