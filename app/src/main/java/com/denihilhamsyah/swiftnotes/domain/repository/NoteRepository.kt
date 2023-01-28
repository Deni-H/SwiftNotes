package com.denihilhamsyah.swiftnotes.domain.repository

import com.denihilhamsyah.swiftnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    fun getAllNotes(): Flow<List<Note>>

}