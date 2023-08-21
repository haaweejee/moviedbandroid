package id.haaweejee.moviedbandroid.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.haaweejee.moviedbandroid.data.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "tmdb.db")
            .build()

    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Provides
    fun provideRatedMovie(db: AppDatabase) = db.ratedMovieDao()
}
