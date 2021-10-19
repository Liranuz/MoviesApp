package com.pilltracker.movies.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.pilltracker.movies.data.database.room.DatabaseConstants.ADULT_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.BACKDROP_PATH_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.DESCRIPTION_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.ID_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.MOVIE_TABLE
import com.pilltracker.movies.data.database.room.DatabaseConstants.ORIGINAL_LANGUAGE_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.ORIGINAL_TITLE_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.POPULARITY_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.POSTER_PATH_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.RATING_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.RELEASE_DATE_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.TITLE_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.VIDEO_COLUMN
import com.pilltracker.movies.data.database.room.DatabaseConstants.VOTE_COUNT_COLUMN
import kotlinx.parcelize.Parcelize

@Entity(tableName = MOVIE_TABLE)
@Parcelize
data class Result (

    @PrimaryKey
    @ColumnInfo(name = ID_COLUMN)
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = POPULARITY_COLUMN)
    @SerializedName("popularity")
    val popularity: Double,

    @ColumnInfo(name = VOTE_COUNT_COLUMN)
    @SerializedName("vote_count")
    val voteCount: Int,

    @ColumnInfo(name = VIDEO_COLUMN)
    @SerializedName("video")
    val video: Boolean,

    @ColumnInfo(name = POSTER_PATH_COLUMN)
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = ADULT_COLUMN)
    @SerializedName("adult")
    val adult: Boolean,

    @ColumnInfo(name = BACKDROP_PATH_COLUMN)
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = ORIGINAL_LANGUAGE_COLUMN)
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = ORIGINAL_TITLE_COLUMN)
    @SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name = TITLE_COLUMN)
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = RATING_COLUMN)
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = DESCRIPTION_COLUMN)
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = RELEASE_DATE_COLUMN)
    @SerializedName("release_date")
    val releaseDate: String,
) : Parcelable