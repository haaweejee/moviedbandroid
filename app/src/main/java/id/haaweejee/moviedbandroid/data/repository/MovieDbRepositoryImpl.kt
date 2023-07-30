package id.haaweejee.moviedbandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import id.haaweejee.moviedbandroid.core.base.BaseRepository
import id.haaweejee.moviedbandroid.data.remote.dto.response.detail.DetailMovieResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenresResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewsResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.video.VideoResponse
import id.haaweejee.moviedbandroid.data.remote.paging.MoviePagingSource
import id.haaweejee.moviedbandroid.data.remote.paging.ReviewPagingSource
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDbRepositoryImpl @Inject constructor(
    private val network: MovieDbNetwork,
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
}
