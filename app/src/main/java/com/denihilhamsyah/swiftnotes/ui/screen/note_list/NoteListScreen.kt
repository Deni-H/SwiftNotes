package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.R
import com.denihilhamsyah.swiftnotes.navigation.Screen
import com.denihilhamsyah.swiftnotes.ui.components.NoteItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController
) {
    val tag = "NoteListScreen"
    val viewModel: NoteListViewModel = hiltViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    val isSelectItem = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateListOf<Int>() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = Screen.AddEditNote.route)
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_note),
                    contentDescription = "add_note"
                )
            }
        }
    ) { paddingValues ->
        if (notes.value.isNotEmpty()) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(paddingValues),
                columns = StaggeredGridCells.Fixed(2)
            ) {
                items(notes.value) {
                    val id: Int = it.id!!
                    NoteItem(
                        note = it,
                        selected = id in selectedItem,
                        onClick = {
                            if (isSelectItem.value) {
                                if (id in selectedItem) {
                                    selectedItem.remove(id)
                                    if (selectedItem.size == 0) {
                                        isSelectItem.value = false
                                    }
                                } else {
                                    selectedItem.add(id)
                                }
                            } else {
                                navController.navigate(route = Screen.AddEditNote.setId(id))
                            }
                        },
                        onLongClick = {
                            selectedItem.add(id)
                            isSelectItem.value = true
                        }
                    )
                }
            }
        } else {
            EmptyScreen()
        }
    }
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.empty_screen_msg),
            style = MaterialTheme.typography.body1,
        )
    }
}