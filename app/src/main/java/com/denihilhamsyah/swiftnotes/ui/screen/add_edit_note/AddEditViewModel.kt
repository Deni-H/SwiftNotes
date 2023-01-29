package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denihilhamsyah.swiftnotes.TAG
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    val id: MutableState<Int?> = mutableStateOf(null)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    fun insertNote() {
        if (title.value.isNotEmpty() || description.value.isNotEmpty()) {
            viewModelScope.launch {
                noteRepository.insertNote(
                    Note(
                        id = id.value,
                        title = title.value,
                        description = description.value
                    )
                )
            }
        } else {
            Log.d(TAG, "Title and description is empty")
        }
    }
}