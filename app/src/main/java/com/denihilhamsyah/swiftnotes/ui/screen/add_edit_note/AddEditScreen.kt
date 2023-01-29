package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.denihilhamsyah.swiftnotes.ui.components.NoteTextField

@Composable
fun AddEditScreen() {
    val viewModel: AddEditViewModel = hiltViewModel()
    val title = viewModel.title
    val description = viewModel.description

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NoteTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    placeHolder = { Text(text = "Title") },
                    imeAction = ImeAction.Next
                )

                Spacer(modifier = Modifier.height(10.dp))

                NoteTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeHolder = { Text(text = "Description") },
                    imeAction = ImeAction.Done
                )
            }
        }
    )
}