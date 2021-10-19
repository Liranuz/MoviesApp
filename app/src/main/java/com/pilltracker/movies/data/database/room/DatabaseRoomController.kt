package com.pilltracker.movies.data.database.room

import android.content.Context
import androidx.room.Room
import com.pilltracker.movies.model.Result
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRoomController @Inject constructor(
    @ApplicationContext private val mAppContext: Context)
    : LocalDatabase {

    var mDatabase: ProjectRoomDatabase? = null

    init {
        mDatabase = Room.databaseBuilder(
            mAppContext,
            ProjectRoomDatabase::class.java, "movies-database")
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun removeMovie(mMovieId: Int) {
        Single.just(true)
            .map { result: Boolean? ->
                mDatabase!!.movieDAO()?.deleteMovie(mMovieId)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun saveMovie(movie: Result) {
        Single.just(true)
            .map { result: Boolean? ->
                mDatabase!!.movieDAO()?.insertMovie(movie)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun getAllSavedMovies(): Single<List<Result>> {
        return mDatabase!!.movieDAO()!!.getFavoriteMovies()

    }


}