package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val notes = noteRepository.getAllNotes()

    fun insertNote(id: Int, title: String, description: String?) {
        viewModelScope.launch {
            noteRepository.insertNote(Note(
                id = id,
                title = title,
                description = description
            )
            )
        }
    }
}