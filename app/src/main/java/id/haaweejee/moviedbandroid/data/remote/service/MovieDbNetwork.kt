package id.haaweejee.moviedbandroid.data.remote.service

import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse

interface MovieDbNetwork {

    suspend fun getGenres(): GenresResponse
    suspend fun getMoviesDiscover(genreId: String? = "", page: Int): MoviesResponse
    suspend fun getMovieDetail(movieId: String): DetailMovieResponse
    suspend fun getMovieReview(movieId: String): ReviewsResponse
    suspend fun getMovieReviewPagination(movieId: String, page: Int): ReviewsResponse
    suspend fun getMovieVideo(movieId: String): VideoResponse
}
