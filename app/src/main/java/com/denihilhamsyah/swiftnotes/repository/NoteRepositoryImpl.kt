package com.denihilhamsyah.swiftnotes.repository

import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.model.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }
}