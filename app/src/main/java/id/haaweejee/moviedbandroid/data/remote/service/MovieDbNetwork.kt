package id.haaweejee.moviedbandroid.data.remote.service

import id.haaweejee.moviedbandroid.data.remote.dto.response.account.AccountResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.accountstate.AccountStateResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.rating.PostRatingResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse

interface MovieDbNetwork {

    suspend fun getGenres(): GenresResponse
    suspend fun getMoviesDiscover(genreId: String? = "", page: Int): MoviesResponse
    suspend fun getMovieDetail(movieId: String): DetailMovieResponse
    suspend fun getMovieReview(movieId: String): ReviewsResponse
    suspend fun getMovieReviewPagination(movieId: String, page: Int): ReviewsResponse
    suspend fun getMovieVideo(movieId: String): VideoResponse
    suspend fun getAccountDetail(): AccountResponse
    suspend fun getMovieRecommendation(movieId: String): MoviesResponse
    suspend fun postMovieRating(movieId: String, rating: Double): PostRatingResponse
    suspend fun deleteMovieRating(movieId: String): PostRatingResponse
    suspend fun getAccountState(movieId: String): AccountStateResponse
    suspend fun getRatedMovie(page: Int): MoviesResponse

}
