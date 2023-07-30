package id.haaweejee.moviedbandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.haaweejee.moviedbandroid.data.repository.MovieDbRepositoryImpl
import id.haaweejee.moviedbandroid.domain.repository.MovieDbRepository

@Module(includes = [KtorModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(repo: MovieDbRepositoryImpl): MovieDbRepository
}
