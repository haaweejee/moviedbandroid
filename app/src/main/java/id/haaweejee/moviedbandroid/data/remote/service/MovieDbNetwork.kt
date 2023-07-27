package id.haaweejee.moviedbandroid.data.remote.service

import id.haaweejee.moviedbandroid.data.remote.dto.response.GenreResponse
import id.haaweejee.moviedbandroid.data.remote.dto.response.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface MovieDbNetwork {

    suspend fun getGenres(): GenreResponse

    suspend fun getMoviesDiscover(): MovieResponse

    companion object {
        fun create(): MovieDbNetwork = MovieDbNetworkImpl(
            service = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            },
        )
    }
}
