package com.denihilhamsyah.swiftnotes.di

import android.app.Application
import androidx.room.Room
import com.denihilhamsyah.swiftnotes.domain.model.NoteDatabase
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }
}