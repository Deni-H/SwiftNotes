package com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.R
import com.denihilhamsyah.swiftnotes.ui.components.NoteTextField

@Composable
fun AddEditScreen(
    navController: NavController,
    noteId: Int
) {
    val viewModel: AddEditViewModel = hiltViewModel()
    viewModel.setId(noteId)

    val title = viewModel.title
    val description = viewModel.description

    Scaffold(
        topBar = {
            TopBar(onClickBack = {
                viewModel.insertNote(navController)
            })
        },
        content = { paddingValues ->
            BackHandler(onBack = {
                viewModel.insertNote(navController)
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

@Composable
fun TopBar(
    onClickBack: () -> Unit,
    content: @Composable () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onClickBack) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back_button"
            )
        }
        content()
    }
}