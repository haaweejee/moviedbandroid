package id.haaweejee.moviedbandroid.ui.component.organism

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.MidNight
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import id.haaweejee.moviedbandroid.ui.viewmodel.RatingViewModel

@Composable
fun RatingDialog(
    movieId: Int,
    setShowDialog: (Boolean) -> Unit,
    isRated: (Boolean) -> Unit,
) {
    val viewModel = hiltViewModel<RatingViewModel>()
    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MidNight,
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Send Rating",
                            fontFamily = latoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = FrostedMint,
                        )
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = FrostedMint,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) },
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = if (txtFieldError.value.isEmpty()) FrostedMint else Color.Red,
                                ),
                                shape = RoundedCornerShape(50),
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            textColor = FrostedMint,
                            cursorColor = FrostedMint,
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "",
                                tint = FrostedMint,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                            )
                        },
                        placeholder = { Text(text = "Enter Rating", color = FrostedMint) },
                        value = txtField.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            txtField.value = it.take(10)
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Button(
                            onClick = {
                                viewModel.deleteRatingMovie(
                                    movieId = movieId.toString(),
                                )
                                isRated(false)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Red,
                            ),
                            modifier = Modifier
                                .weight(0.5f)
                                .height(50.dp)
                                .padding(end = 4.dp),
                        ) {
                            Text(
                                text = "Delete",
                                color = Color.White,
                            )
                        }
                        Button(
                            onClick = {
                                viewModel.postRatingMovie(
                                    movieId = movieId.toString(),
                                    rating = txtField.value.toDouble(),
                                )
                                isRated(true)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Blumine,
                            ),
                            modifier = Modifier
                                .weight(0.5f)
                                .padding(start = 4.dp)
                                .height(50.dp),
                        ) {
                            Text(
                                text = "Send",
                                color = FrostedMint,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRatingDialog() {
    RatingDialog(
        movieId = 1,
        setShowDialog = {},
        isRated = {},
    )
}
