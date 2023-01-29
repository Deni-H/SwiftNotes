package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.ui.components.TopBar
import com.denihilhamsyah.swiftnotes.ui.components.NoteTextField

@Composable
fun AddEditScreen(
    navController: NavController
) {
    val viewModel: AddEditViewModel = hiltViewModel()
    val title = viewModel.title
    val description = viewModel.description

    Scaffold(
        topBar = { TopBar(navController)},
        content = { paddingValues ->
            BackHandler(onBack = {
                viewModel.insertNote()
                navController.popBackStack()
            })
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