package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.util.TAG
import com.denihilhamsyah.swiftnotes.domain.model.Note
import com.denihilhamsyah.swiftnotes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val id: MutableState<Int?> = mutableStateOf(null)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")

    fun setId(id: Int) {
        if (id != -1) {
            this.id.value = id
            Log.d(TAG, this.id.value.toString())
            viewModelScope.launch(Dispatchers.IO) {
                val result = noteRepository.getNoteById(id)!!
                launch(Dispatchers.Main) {
                    title.value = result.title!!
                    description.value = result.description!!
                }
            }
        }
    }

    fun insertNote(navController: NavController) {
        if (title.value.isNotEmpty() || description.value.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
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
        navController.popBackStack()
    }
}