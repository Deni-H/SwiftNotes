package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val notes = noteRepository.getAllNotes()

    fun deleteNote(items: SnapshotStateList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            for (i in items) {
                noteRepository.deleteNote(getNoteById(i))
                Log.d(TAG, "Removing $i")
            }
            items.clear()
        }
    }
    private suspend fun getNoteById(id: Int): Note {
        return withContext(Dispatchers.IO) {
            noteRepository.getNoteById(id)!!
        }
    }
}