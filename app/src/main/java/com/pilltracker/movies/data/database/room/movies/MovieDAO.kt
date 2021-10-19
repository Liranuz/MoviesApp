package com.pilltracker.movies.data.database.room.movies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pilltracker.movies.data.database.room.DatabaseConstants
import com.pilltracker.movies.model.Result
import io.reactivex.Single

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Result)

    @Query("DELETE FROM " + DatabaseConstants.MOVIE_TABLE.toString() + " WHERE " + DatabaseConstants.ID_COLUMN + " =:movieId")
    fun deleteMovie(movieId: Int?)

    @Query("SELECT * FROM " + DatabaseConstants.MOVIE_TABLE)
    fun getFavoriteMovies (): Single<List<Result>>
}