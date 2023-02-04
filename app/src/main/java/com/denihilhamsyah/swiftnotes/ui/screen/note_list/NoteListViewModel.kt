package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    private val query = MutableStateFlow("")
    private val _notes = noteRepository.getAllNotes()

    @OptIn(ExperimentalCoroutinesApi::class)
    val notes = query.flatMapLatest { query -> searchNotes(query) }

    fun setQuery(query: String) {
        this.query.value = query
    }

    private fun searchNotes(query: String): Flow<List<Note>> {
        return _notes.map { item ->
            item.filter {
                !it.archived && (it.title!!.contains(query) || it.description!!.contains(query))
            }
        }
    }

    fun deleteNote(items: SnapshotStateList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in items) {
                val note = noteRepository.getNoteById(i)!!
                noteRepository.deleteNote(note)
                Log.d(TAG, "Removing $i")
            }
            items.clear()
        }
    }

    fun archiveNote(items: SnapshotStateList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in items) {
                val note = noteRepository.getNoteById(i)!!
                noteRepository.insertNote(
                    Note(
                        id = note.id,
                        title = note.title,
                        description = note.description,
                        archived = !note.archived
                    )
                )
                Log.d(TAG, "Note $i archived = ${!note.archived}")
            }
            items.clear()
        }
    }
}