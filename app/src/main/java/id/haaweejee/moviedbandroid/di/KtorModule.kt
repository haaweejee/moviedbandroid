package id.haaweejee.moviedbandroid.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetwork
import id.haaweejee.moviedbandroid.data.remote.service.MovieDbNetworkImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
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
    private fun okhttpEngine(context: Context): HttpClientEngine = OkHttp.create {
        val chuckerInterceptor = ChuckerInterceptor.Builder(context = context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()

        addInterceptor(chuckerInterceptor)
    }

    @Provides
    @Singleton
    fun provideKtorHttpClient(@ApplicationContext context: Context): HttpClient =
        HttpClient(okhttpEngine(context)) {
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
