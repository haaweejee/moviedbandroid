package id.haaweejee.moviedbandroid.ui.component.organism.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import id.haaweejee.moviedbandroid.domain.entities.MovieDiscoverEntities
import id.haaweejee.moviedbandroid.ui.component.molecules.MovieCard
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.CustomLoading
import id.haaweejee.moviedbandroid.ui.component.molecules.PaginationLoading

@Composable
fun HomeMoviesContent(
    modifier: Modifier = Modifier,
    content: LazyPagingItems<MovieDiscoverEntities>,
    navigateToDetail: (Int) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    LazyColumn(
        modifier.padding(
            horizontal = 12.dp,
        ),
    ) {
        items(
            items = content,
            key = { it.idMovie },
        ) { movie ->
            MovieCard(
                data = movie!!,
                modifier = modifier.clickable {
                    navigateToDetail(movie.idMovie)
                },
            )
        }

        when (content.loadState.refresh) { // FIRST LOAD
            is LoadState.Error -> {
                item {
                    CustomSnackbar(
                        message = "Data Not Available, Try Again Later",
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
            is LoadState.Loading -> { // Loading UI
                item {
                    CustomLoading(modifier = modifier.fillParentMaxSize())
                }
            }
            else -> {
                item {
                    CustomSnackbar(
                        message = "Data Not Available, Try Again Later",
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }

        when (content.loadState.append) { // Pagination
            is LoadState.Error -> {
                item {
                    CustomSnackbar(
                        message = "Data Not Available, Try Again Later",
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    PaginationLoading()
                }
            }
            else -> {
                item {
                    CustomSnackbar(
                        message = "Data Not Available, Try Again Later",
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }
}
