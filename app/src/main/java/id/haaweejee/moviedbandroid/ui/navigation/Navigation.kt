package id.haaweejee.moviedbandroid.ui.navigation

sealed class Navigation(val route: String) {
    object HomeMovie : Navigation("home")
    object GenreMovie : Navigation("genre/{genreId}") {
        fun createRoute(genreId: Int) = "genre/$genreId"
    }
    object DetailMovie : Navigation("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
