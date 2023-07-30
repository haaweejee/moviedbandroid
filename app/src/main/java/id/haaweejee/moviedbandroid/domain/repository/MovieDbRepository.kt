package id.haaweejee.moviedbandroid.domain.repository

import androidx.paging.PagingData
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import kotlinx.coroutines.flow.Flow

interface MovieDbRepository {

    fun getGenres(): Flow<GenresResponse>

    fun getMoviesDiscover(genreId: String? = ""): Flow<PagingData<MovieResponse>>

    fun getMovieDetail(movieId: String): Flow<DetailMovieResponse>

    fun getMovieReview(movieId: String): Flow<ReviewsResponse>

    fun getMovieReviewPagination(movieId: String): Flow<PagingData<ReviewResponse>>

    fun getMovieVideo(movieId: String): Flow<VideoResponse>
}
