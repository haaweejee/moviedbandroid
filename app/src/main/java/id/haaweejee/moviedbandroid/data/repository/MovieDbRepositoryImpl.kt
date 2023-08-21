package id.haaweejee.moviedbandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.haaweejee.moviedbandroid.core.base.BaseRepository
import id.haaweejee.moviedbandroid.data.local.dao.MovieDao
import id.haaweejee.moviedbandroid.data.local.dao.RatedMovieDao
import id.haaweejee.moviedbandroid.data.local.entity.LocalMovieEntity
import id.haaweejee.moviedbandroid.data.local.entity.RatedMovieEntity
import id.haaweejee.moviedbandroid.data.remote.dto.response.account.AccountResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.accountstate.AccountStateResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MoviesResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.rating.PostRatingResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import id.haaweejee.moviedbandroid.data.remote.paging.MoviePagingSource
import id.haaweejee.moviedbandroid.data.remote.paging.RatedMoviePagingSource
import id.haaweejee.moviedbandroid.data.remote.paging.ReviewPagingSource
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDbRepositoryImpl @Inject constructor(
    private val network: MovieDbNetwork,
    private val movieDao: MovieDao,
    private val ratedMovieDao: RatedMovieDao,
) : BaseRepository(), MovieDbRepository {

    override fun getGenres(): Flow<GenresResponse> = flow {
        safeNetworkCall {
            emit(network.getGenres())
        }
    }

    override fun getMoviesDiscover(genreId: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                MoviePagingSource(network, genreId)
            },
        ).flow

    override fun getMovieDetail(movieId: String): Flow<DetailMovieResponse> {
        return flow {
            safeNetworkCall {
                emit(network.getMovieDetail(movieId))
            }
        }
    }

    override fun getMovieReview(movieId: String): Flow<ReviewsResponse> {
        return flow {
            safeNetworkCall {
                emit(network.getMovieReview(movieId))
            }
        }
    }

    override fun getMovieReviewPagination(movieId: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = {
            ReviewPagingSource(network, movieId)
        },
    ).flow

    override fun getMovieVideo(movieId: String): Flow<VideoResponse> {
        return flow {
            safeNetworkCall {
                emit(network.getMovieVideo(movieId))
            }
        }
    }

    override suspend fun insertMovieToBookmark(movie: LocalMovieEntity) =
        movieDao.insertMovie(movie)

    override suspend fun deleteMovieBookmarked(movieId: Int) =
        movieDao.deleteMovieById(movieId)

    override fun getMovieBookmarkById(movieId: Int): Flow<LocalMovieEntity?> =
        movieDao.getMovieById(movieId)

    override fun getMoviesBookmarked(): Flow<List<LocalMovieEntity>> =
        movieDao.getAllMovies()
    override fun getAccountDetail(): Flow<AccountResponse> =
        flow {
            safeNetworkCall {
                emit(network.getAccountDetail())
            }
        }

    override fun getMovieRecommendation(movieId: String): Flow<MoviesResponse> =
        flow {
            safeNetworkCall {
                emit(network.getMovieRecommendation(movieId))
            }
        }

    override fun postMovieRating(movieId: String, rating: Double): Flow<PostRatingResponse> =
        flow {
            safeNetworkCall {
                emit(network.postMovieRating(movieId, rating))
            }
        }

    override fun deleteMovieRating(movieId: String): Flow<PostRatingResponse> =
        flow {
            safeNetworkCall {
                emit(network.deleteMovieRating(movieId))
            }
        }

    override suspend fun insertRatedMovie(movie: RatedMovieEntity) =
        ratedMovieDao.insertRatedMovie(movie)

    override suspend fun deleteRatedMovieById(movieId: Int) =
        ratedMovieDao.deleteRatedMovieById(movieId)

    override fun getRatedMovieById(movieId: Int): Flow<RatedMovieEntity?> =
        ratedMovieDao.getRatedMovieById(movieId)

    override fun getAccountState(movieId: String): Flow<AccountStateResponse> =
        flow {
            safeNetworkCall {
                emit(network.getAccountState(movieId))
            }
        }

    override fun getMoviesRated(): Flow<PagingData<MovieResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                RatedMoviePagingSource(network)
            },
        ).flow
}
