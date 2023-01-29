package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.denihilhamsyah.swiftnotes.R
import com.denihilhamsyah.swiftnotes.ui.components.NoteItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen() {
    val viewModel: NoteListViewModel = hiltViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.add_note),
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
                                    Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
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
            text = "Notes you add appears here",
            style = MaterialTheme.typography.body1,
        )
    }
}