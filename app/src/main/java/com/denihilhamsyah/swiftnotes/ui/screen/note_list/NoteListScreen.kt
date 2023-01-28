package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.denihilhamsyah.swiftnotes.ui.components.NoteItem

@Composable
fun NoteListScreen() {

    val viewModel: NoteListViewModel = hiltViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            viewModel.insertNote(
                id = 1,
                title = "That's it",
                description = "Congratulations you have successfully learn font family customization in android jetpack compose."
            )
        }) {
            Text(text = "Add example note")
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(notes.value) {
                NoteItem(note = it)
            }
        }
    }
}