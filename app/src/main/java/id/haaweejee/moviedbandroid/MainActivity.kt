package id.haaweejee.moviedbandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.haaweejee.moviedbandroid.data.remote.dto.response.GenreResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.MovieResponse
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork
import id.haaweejee.moviedbandroid.ui.compose.CardGenre
import id.haaweejee.moviedbandroid.ui.compose.CardMovie
import id.haaweejee.moviedbandroid.ui.theme.MoviedbandroidTheme

class MainActivity : ComponentActivity() {

    private val service = MovieDbNetwork.create()

    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val genres = produceState(
                initialValue = GenreResponse(emptyList()),
                producer = {
                    value = service.getGenres()
                },
            )
            val movies = produceState(
                initialValue = MovieResponse(
                    page = 0,
                    results = emptyList(),
                    total_pages = 0,
                    total_results = 0,
                ),
                producer = {
                    value = service.getMoviesDiscover()
                },
            )
            MoviedbandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column {
                        Text(
                            text = "MovieDB Android",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 28.sp,
                        )
                        Text(
                            text = "Genre List",
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 4.dp,
                                bottom = 16.dp,
                            ),
                            fontSize = 20.sp,
                        )
                        FlowRow(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp,
                            ),
                        ) {
                            genres.value.genres.forEach {
                                CardGenre(genre = it)
                            }
                        }
                        LazyColumn(
                            Modifier.padding(
                                horizontal = 16.dp,
                            ),
                        ) {
                            items(movies.value.results) {
                                CardMovie(data = it)
                            }
                        }
                    }
                }
            }
        }
    }
}
