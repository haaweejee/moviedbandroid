package id.haaweejee.moviedbandroid.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.haaweejee.moviedbandroid.data.remote.dto.response.movielist.MovieResponse
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork

class MoviePagingSource(
    private val service: MovieDbNetwork,
    private val genreId: String? = "",
) : PagingSource<Int, MovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> =
        try {
            val page = params.key ?: 1
            val response = service.getMoviesDiscover(genreId, page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}
