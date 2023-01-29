package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import androidx.lifecycle.ViewModel
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    noteRepository: NoteRepository
): ViewModel() {
    val notes = noteRepository.getAllNotes()
}