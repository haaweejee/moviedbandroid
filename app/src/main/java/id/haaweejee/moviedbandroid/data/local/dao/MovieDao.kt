package id.haaweejee.moviedbandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.haaweejee.moviedbandroid.data.local.entity.LocalMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<LocalMovieEntity>>

    @Query("SELECT * FROM movie WHERE idMovie = :idMovie")
    fun getMovieById(idMovie: Int): Flow<LocalMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(data: LocalMovieEntity)

    @Query("DELETE FROM movie WHERE idMovie = :idMovie")
    fun deleteMovieById(idMovie: Int)
}
