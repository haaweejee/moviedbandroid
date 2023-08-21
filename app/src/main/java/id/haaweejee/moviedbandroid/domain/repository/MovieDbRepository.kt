package id.haaweejee.moviedbandroid.domain.repository

import androidx.paging.PagingData
import id.haaweejee.moviedbandroid.data.local.entity.LocalMovieEntity
import id.haaweejee.moviedbandroid.data.local.entity.RatedMovieEntity
import id.haaweejee.moviedbandroid.data.remote.dto.response.account.AccountResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.accountstate.AccountStateResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.rating.PostRatingResponse
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

    suspend fun insertMovieToBookmark(movie: LocalMovieEntity)

    suspend fun deleteMovieBookmarked(movieId: Int)

    fun getMovieBookmarkById(movieId: Int): Flow<LocalMovieEntity?>

    fun getMoviesBookmarked(): Flow<List<LocalMovieEntity>>

    fun getAccountDetail(): Flow<AccountResponse>

    fun getMovieRecommendation(movieId: String): Flow<MoviesResponse>

    fun postMovieRating(movieId: String, rating: Double): Flow<PostRatingResponse>

    fun deleteMovieRating(movieId: String): Flow<PostRatingResponse>

    suspend fun insertRatedMovie(movie: RatedMovieEntity)

    suspend fun deleteRatedMovieById(movieId: Int)

    fun getRatedMovieById(movieId: Int): Flow<RatedMovieEntity?>

    fun getAccountState(movieId: String): Flow<AccountStateResponse>

    fun getMoviesRated(): Flow<PagingData<MovieResponse>>
}
