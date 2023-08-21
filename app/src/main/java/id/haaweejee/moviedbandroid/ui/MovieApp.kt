package id.haaweejee.moviedbandroid.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.haaweejee.moviedbandroid.ui.component.molecules.BottomBar
import id.haaweejee.moviedbandroid.ui.navigation.Navigation
import id.haaweejee.moviedbandroid.ui.screen.AboutAppsScreen
import id.haaweejee.moviedbandroid.ui.screen.AccountScreen
import id.haaweejee.moviedbandroid.ui.screen.BookmarkScreen
import id.haaweejee.moviedbandroid.ui.screen.DetailMovieScreen
import id.haaweejee.moviedbandroid.ui.screen.GenreMoviesScreen
import id.haaweejee.moviedbandroid.ui.screen.HomeScreen
import id.haaweejee.moviedbandroid.ui.screen.RatedScreen
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
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Navigation.HomeMovie.route,
        Navigation.Account.route,
        -> true
        else -> false
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier,
        containerColor = MidNight,
        bottomBar = {
            if (showBottomBar) BottomBar(navController)
        },
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
            composable(route = Navigation.Account.route) {
                AccountScreen(
                    snackbarHostState = snackbarHostState,
                    navigate = {
                        when (it) {
                            "bookmark" -> navController.navigate(Navigation.Bookmark.route)
                            "rated_movies_tv" -> navController.navigate(Navigation.Rated.route)
                            "about" -> navController.navigate(Navigation.About.route)
                        }
                    },
                )
            }
            composable(route = Navigation.Bookmark.route) {
                BookmarkScreen(
                    snackbarHostState = snackbarHostState,
                    navController = navController,
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.DetailMovie.createRoute(movieId),
                        )
                    },
                )
            }
            composable(route = Navigation.Rated.route) {
                RatedScreen(
                    snackbarHostState = snackbarHostState,
                    navController = navController,
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.DetailMovie.createRoute(movieId),
                        )
                    },
                )
            }
            composable(route = Navigation.About.route) {
                AboutAppsScreen(
                    snackbarHostState = snackbarHostState,
                    navController = navController,
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
                    navigateToDetail = { movieId ->
                        navController.navigate(
                            Navigation.DetailMovie.createRoute(movieId),
                        )
                    },
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
