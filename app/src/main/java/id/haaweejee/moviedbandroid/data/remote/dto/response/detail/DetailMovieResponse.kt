package id.haaweejee.moviedbandroid.data.remote.dto.response.detail

import id.haaweejee.moviedbandroid.data.remote.dto.response.genre.GenreResponse
import kotlinx.serialization.Serializable

@Serializable
data class DetailMovieResponse(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val belongs_to_collection: Collection? = null,
    val budget: Int? = 0,
    val genres: List<GenreResponse>? = emptyList(),
    val homepage: String? = "",
    val id: Int? = 0,
    val imdb_id: String? = "",
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany>? = emptyList(),
    val production_countries: List<ProductionCountry>? = emptyList(),
    val release_date: String? = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spoken_languages: List<SpokenLanguage>? = emptyList(),
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0,
)
