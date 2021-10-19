package com.pilltracker.movies.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pilltracker.movies.data.database.room.movies.MovieDAO
import com.pilltracker.movies.model.Result


@Database(entities = [Result::class], version = 6, exportSchema = false)
abstract class ProjectRoomDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO?
}