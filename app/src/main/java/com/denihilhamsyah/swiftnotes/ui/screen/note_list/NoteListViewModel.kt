package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val notes = noteRepository.getAllNotes().map {
        it.filter { item ->
            !item.archived
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