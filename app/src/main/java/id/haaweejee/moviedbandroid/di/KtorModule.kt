package id.haaweejee.moviedbandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.haaweejee.moviedbandroid.App
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetworkImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    // creating the Ktor HttpClienEngine
    val okhttpEngine = OkHttp.create {
        App().chuckerInterceptor?.let {
            addInterceptor(it)
        }
    }

    @Provides
    @Singleton
    fun provideKtorHttpClient(): HttpClient = HttpClient(okhttpEngine) {
        expectSuccess = true
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            gson()
        }
    }

    @Provides
    @Singleton
    fun provideMovieDbNetwork(client: HttpClient): MovieDbNetwork = MovieDbNetworkImpl(client)
}
