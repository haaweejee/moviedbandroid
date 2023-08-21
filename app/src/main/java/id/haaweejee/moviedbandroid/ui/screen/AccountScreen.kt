package id.haaweejee.moviedbandroid.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.haaweejee.moviedbandroid.R
import id.haaweejee.moviedbandroid.ui.common.UiState
import id.haaweejee.moviedbandroid.ui.component.atom.CustomSnackbar
import id.haaweejee.moviedbandroid.ui.component.molecules.AccountItemCard
import id.haaweejee.moviedbandroid.ui.component.molecules.CustomLoading
import id.haaweejee.moviedbandroid.ui.theme.Blumine
import id.haaweejee.moviedbandroid.ui.theme.FrostedMint
import id.haaweejee.moviedbandroid.ui.viewmodel.AccountViewModel

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val viewModel = hiltViewModel<AccountViewModel>()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TopAppBar(
            backgroundColor = Blumine,
            title = {
                Text("Account", color = FrostedMint)
            },
        )
        viewModel.accountContentState.collectAsState(UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    CustomLoading(modifier = modifier.fillMaxSize())
                    viewModel.getAccountDetail()
                }
                is UiState.Success -> {
                    Spacer(modifier = modifier.height(16.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(uiState.data.header.avatarImage)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = "poster_path",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .clip(CircleShape)
                            .height(125.dp)
                            .width(125.dp),
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = uiState.data.header.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = FrostedMint,
                    )
                    Spacer(modifier = modifier.height(4.dp))
                    Text(text = uiState.data.header.userName, fontSize = 16.sp, color = FrostedMint)
                    Spacer(modifier = modifier.height(16.dp))
                    LazyColumn {
                        items(uiState.data.list) {
                            AccountItemCard(
                                icon = it.icon,
                                title = it.title,
                                modifier = modifier
                                    .clickable {
                                        navigate(it.name)
                                    },
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    CustomSnackbar(
                        message = "Data Not Available, Try Again Later",
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }
}
