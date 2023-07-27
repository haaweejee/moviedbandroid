package id.haaweejee.moviedbandroid.data.remote.service

import android.util.Log
import id.haaweejee.moviedbandroid.data.remote.dto.response.GenreResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieDbNetworkImpl(
    private val service: HttpClient,
) : MovieDbNetwork {

    private val _baseUrl = "https://api.themoviedb.org/3/"
    private val _moviesGenres = "genre/movie/list"
    private val _moviesDiscover = "discover/movie"
    private val _accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMTMxYzY0YzlmMDQ3ZjNmZGQ0ZTUzNzU1ZjFkYmIxNCIsInN1YiI6IjVkYzdhNmE3YWIxYmM3MDAxNTA0MjVkMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cFf8TMXk4i_jG7eDOB2wE99GU4AyN-x2RIpKoMiPwAE"

    override suspend fun getGenres(): GenreResponse =
        try {
            service.get {
                url(_baseUrl + _moviesGenres)
                header("Authorization", "Bearer $_accessToken")
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            Log.d("MovieDbNetworkImpl", "getGenre: ${e.response.status.description}")
            GenreResponse(emptyList())
        } catch (e: ClientRequestException) {
            // 4xx
            Log.d("MovieDbNetworkImpl", "getGenre: ${e.response.status.description}")
            GenreResponse(emptyList())
        } catch (e: ServerResponseException) {
            // 5xx
            Log.d("MovieDbNetworkImpl", "getGenre: ${e.response.status.description}")
            GenreResponse(emptyList())
        } catch (e: Exception) {
            Log.d("MovieDbNetworkImpl", "getGenre: ${e.message}")
            GenreResponse(emptyList())
        }

    override suspend fun getMoviesDiscover(): MovieResponse =
        try {
            service.get {
                url(_baseUrl + _moviesDiscover)
                header("Authorization", "Bearer $_accessToken")
                parameter("with_genres", "27")
            }
        } catch (e: RedirectResponseException) {
            // 3xx
            Log.d("MovieDbNetworkImpl", "getMovie: ${e.response.status.description}")
            MovieResponse(
                page = 0,
                results = emptyList(),
                total_pages = 0,
                total_results = 0,
            )
        } catch (e: ClientRequestException) {
            Log.d("MovieDbNetworkImpl", "getMovie: ${e.response.status.description}")
            // 4xx
            MovieResponse(
                page = 0,
                results = emptyList(),
                total_pages = 0,
                total_results = 0,
            )
        } catch (e: ServerResponseException) {
            Log.d("MovieDbNetworkImpl", "getMovie: ${e.response.status.description}")
            // 5xx
            MovieResponse(
                page = 0,
                results = emptyList(),
                total_pages = 0,
                total_results = 0,
            )
        } catch (e: Exception) {
            Log.d("MovieDbNetworkImpl", "getMovie: ${e.message}")
            MovieResponse(
                page = 0,
                results = emptyList(),
                total_pages = 0,
                total_results = 0,
            )
        }
}
