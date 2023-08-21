package id.haaweejee.moviedbandroid.data.remote.dto.response.accountstate

data class AccountStateResponse(
    val favorite: Boolean,
    val id: Int,
    val rated: Any,
    val watchlist: Boolean,
)
