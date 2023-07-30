package id.haaweejee.moviedbandroid.data.remote.service

import id.haaweejee.moviedbandroid.BuildConfig
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class MovieDbNetworkImpl(
    private val service: HttpClient,
) : MovieDbNetwork {

    val accessToken = BuildConfig.ACCESS_TOKEN
    override suspend fun getGenres(): GenresResponse =
        service.get(baseUrl + moviesGenres) {
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getMoviesDiscover(genreId: String?, page: Int): MoviesResponse =
        service.get {
            url(baseUrl + moviesDiscover)
            header("Authorization", "Bearer $accessToken")
            parameter("with_genres", genreId)
            parameter("page", page)
        }.body()

    override suspend fun getMovieDetail(movieId: String): DetailMovieResponse =
        service.get {
            url(baseUrl + movieDetail + movieId)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getMovieReview(movieId: String): ReviewsResponse =
        service.get {
            url(baseUrl + movieDetail + movieId + movieReviews)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getMovieReviewPagination(movieId: String, page: Int): ReviewsResponse =
        service.get {
            url(baseUrl + movieDetail + movieId + movieReviews)
            header("Authorization", "Bearer $accessToken")
            parameter("page", page)
        }.body()

    override suspend fun getMovieVideo(movieId: String): VideoResponse =
        service.get {
            url(baseUrl + movieDetail + movieId + movieVideos)
            header("Authorization", "Bearer $accessToken")
        }.body()
}
