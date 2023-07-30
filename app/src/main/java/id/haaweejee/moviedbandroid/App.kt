package id.haaweejee.moviedbandroid

import android.app.Application
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.hilt.android.HiltAndroidApp
import id.haaweejee.moviedbandroid.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("Application Start")
    }

    val chuckerInterceptor = appContext?.let {
        ChuckerInterceptor.Builder(it)
            .collector(ChuckerCollector(it))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()
    }

    companion object {
        var appContext: Context? = null
    }
}
