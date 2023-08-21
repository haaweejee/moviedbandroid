package id.haaweejee.moviedbandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.haaweejee.moviedbandroid.data.local.dao.MovieDao
import id.haaweejee.moviedbandroid.data.local.dao.RatedMovieDao
import id.haaweejee.moviedbandroid.data.local.entity.LocalMovieEntity
import id.haaweejee.moviedbandroid.data.local.entity.RatedMovieEntity

@Database(
    entities = [
        LocalMovieEntity::class,
        RatedMovieEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun ratedMovieDao(): RatedMovieDao
}
