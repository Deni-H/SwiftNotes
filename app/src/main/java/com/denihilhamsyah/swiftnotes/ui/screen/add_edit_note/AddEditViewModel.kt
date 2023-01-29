package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    fun insertNote(
        id: Int? = null,
        title: String,
        description: String?
    ) {
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                id = id,
                title = title,
                description = description
            ))
        }
    }
}