package id.haaweejee.moviedbandroid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.haaweejee.moviedbandroid.data.local.entity.RatedMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RatedMovieDao {

    @Query("SELECT * FROM rated_movie WHERE idMovie = :idMovie")
    fun getRatedMovieById(idMovie: Int): Flow<RatedMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRatedMovie(data: RatedMovieEntity)

    @Query("DELETE FROM movie WHERE idMovie = :idMovie")
    fun deleteRatedMovieById(idMovie: Int)
}
