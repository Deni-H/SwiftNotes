package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.R
import com.denihilhamsyah.swiftnotes.TAG
import com.denihilhamsyah.swiftnotes.navigation.Screen
import com.denihilhamsyah.swiftnotes.ui.components.NoteItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController
) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())

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
        },
        content = { paddingValues ->
            if(notes.value.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(paddingValues),
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        items(notes.value) {
                            NoteItem(
                                note = it,
                                onClick = {
                                    Log.d(TAG, it.id.toString())
                                }
                            )
                        }
                    }
                )
            } else {
                EmptyScreen()
            }
        }
    )
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