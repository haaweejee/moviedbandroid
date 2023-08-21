package id.haaweejee.moviedbandroid.data.remote.service

import id.haaweejee.moviedbandroid.BuildConfig
import id.haaweejee.moviedbandroid.data.remote.dto.request.rating.PostRatingRequest
import id.haaweejee.moviedbandroid.data.remote.dto.response.account.AccountResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.accountstate.AccountStateResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.rating.PostRatingResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import javax.inject.Singleton

@Singleton
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

    override suspend fun getAccountDetail(): AccountResponse =
        service.get {
            url(baseUrl + accountDetail)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getMovieRecommendation(movieId: String): MoviesResponse =
        service.get {
            url(baseUrl + movieDetail + movieId + moviesRecommendation)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun postMovieRating(movieId: String, rating: Double): PostRatingResponse =
        service.post {
            url(baseUrl + movieDetail + movieId + movieRating)
            setBody(PostRatingRequest(rating))
            headers {
                header("Accept", "application/json")
                header("Content-Type", "application/json;charset=utf-8")
                header("Authorization", "Bearer $accessToken")
            }
        }.body()

    override suspend fun deleteMovieRating(movieId: String): PostRatingResponse =
        service.delete {
            url(baseUrl + movieDetail + movieId + movieRating)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getAccountState(movieId: String): AccountStateResponse =
        service.get {
            url(baseUrl + movieDetail + movieId + accountState)
            header("Authorization", "Bearer $accessToken")
        }.body()

    override suspend fun getRatedMovie(page: Int): MoviesResponse =
        service.get {
            url("$baseUrl$accountDetail/8808767$accountRatedMovies")
            header("Authorization", "Bearer $accessToken")
        }.body()
}

