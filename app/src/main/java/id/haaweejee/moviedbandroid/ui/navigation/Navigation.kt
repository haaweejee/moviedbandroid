package id.haaweejee.moviedbandroid.ui.navigation

sealed class Navigation(val route: String) {
    object HomeMovie : Navigation("home")
    object Account : Navigation("account")

    object Bookmark : Navigation("bookmark")
    object Rated : Navigation("rated")
    object About : Navigation("about")
    object GenreMovie : Navigation("genre/{genreId}") {
        fun createRoute(genreId: Int) = "genre/$genreId"
    }
    object DetailMovie : Navigation("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
