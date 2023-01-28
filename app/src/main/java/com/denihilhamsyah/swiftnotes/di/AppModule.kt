package com.denihilhamsyah.swiftnotes.di

import android.content.Context
import com.denihilhamsyah.swiftnotes.NoteApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteApp(@ApplicationContext app: Context): NoteApp {
        return app as NoteApp
    }
}