package com.pilltracker.movies.di.module.RoomModule

import android.content.Context
import com.pilltracker.movies.data.RepositoryController
import com.pilltracker.movies.data.RepositoryControllerImpl
import com.pilltracker.movies.data.database.room.DatabaseRoomController
import com.pilltracker.movies.data.database.room.LocalDatabase
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {


    @Provides
    @Singleton
    fun provideRepositoryController(repositoryController: RepositoryControllerImpl): RepositoryController {
        return repositoryController
    }


    @Provides
    @Singleton
    fun proviesDatabase( @ApplicationContext context: Context): LocalDatabase {
        return DatabaseRoomController(context)
    }
}