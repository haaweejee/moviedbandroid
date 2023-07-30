package id.haaweejee.moviedbandroid.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.haaweejee.moviedbandroid.data.remote.dto.response.review.ReviewResponse
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork

class ReviewPagingSource(
    private val service: MovieDbNetwork,
    private val movieId: String
) : PagingSource<Int, ReviewResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResponse> = try {
        val page = params.key ?: 1
        val response = service.getMovieReviewPagination(movieId, page)

        LoadResult.Page(
            data = response.results,
            prevKey = if (page == 1) null else page.minus(1),
            nextKey = if (response.results.isEmpty()) null else page + 1,
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}
