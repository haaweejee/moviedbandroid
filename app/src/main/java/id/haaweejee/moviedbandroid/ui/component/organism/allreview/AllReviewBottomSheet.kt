@file:OptIn(ExperimentalMaterial3Api::class)

package id.haaweejee.moviedbandroid.ui.component.organism.allreview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.ReviewCard
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.theme.MidNight
import id.haaweejee.moviedbandroid.ui.theme.latoFontFamily
import id.haaweejee.moviedbandroid.ui.viewmodel.AllReviewViewModel

@Composable
fun AllReviewBottomSheet(
    onDismiss: () -> Unit,
    movieId: String,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
) {
    val viewModel = hiltViewModel<AllReviewViewModel>()
    val modalBottomSheetState = rememberModalBottomSheetState()

    val allReview = viewModel.getMovieAllReview(movieId).collectAsLazyPagingItems()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = MidNight,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
    ) {
        Column(
            modifier.padding(
                horizontal = 12.dp,
            ).fillMaxSize(),
        ) {
            Text(
                text = "All Review",
                fontFamily = latoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = FrostedMint,
                modifier = modifier.padding(horizontal = 6.dp),
            )
            Spacer(modifier = modifier.height(16.dp))
            LazyColumn {
                items(
                    items = allReview,
                    key = { it.reviewId },
                ) { review ->
                    if (review != null) {
                        ReviewCard(review = review)
                    }
                }
                when (allReview.loadState.refresh) { // FIRST LOAD
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
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = FrostedMint)
                            }
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

                when (allReview.loadState.append) { // Pagination
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = FrostedMint)
                            }
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
    }
}
