package id.haaweejee.moviedbandroid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.haaweejee.moviedbandroid.ui.navigation.Navigation
import id.haaweejee.moviedbandroid.ui.screen.DetailMovieScreen
import id.haaweejee.moviedbandroid.ui.screen.GenreMoviesScreen
import id.haaweejee.moviedbandroid.ui.screen.HomeScreen
import id.haaweejee.moviedbandroid.ui.theme.MidNight
import id.haaweejee.moviedbandroid.ui.util.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val networkConnectivity by connectivityState()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
        containerColor = MidNight,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.HomeMovie.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Navigation.HomeMovie.route) {
                HomeScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.DetailMovie.createRoute(movieId),
                        )
                    },
                    navigateToGenreMovie = { genreId ->
                        navController.navigate(
                            Navigation.GenreMovie.createRoute(genreId),
                        )
                    },
                    networkConnectivity = networkConnectivity,
                    snackbarHostState = snackbarHostState,
                )
            }
            composable(
                route = Navigation.DetailMovie.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            ) {
                val movieId = it.arguments?.getInt("movieId")
                DetailMovieScreen(
                    movieId = movieId ?: 0,
                    navController = navController,
                    networkConnectivity = networkConnectivity,
                    snackbarHostState = snackbarHostState,
                )
            }
            composable(
                route = Navigation.GenreMovie.route,
                arguments = listOf(navArgument("genreId") { type = NavType.IntType }),
            ) {
                val genreId = it.arguments?.getInt("genreId")
                GenreMoviesScreen(
                    genreId = genreId ?: 0,
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.DetailMovie.createRoute(movieId),
                        )
                    },
                    navController = navController,
                    networkConnectivity = networkConnectivity,
                    snackbarHostState = snackbarHostState,
                )
            }
        }
    }
}
