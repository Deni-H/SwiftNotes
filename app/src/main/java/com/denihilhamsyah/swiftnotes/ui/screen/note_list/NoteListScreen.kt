package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                items(notes.value) {
                    NoteItem(note = it)
                }
            }
        )
    }
}