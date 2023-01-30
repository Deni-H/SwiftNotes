package com.denihilhamsyah.swiftnotes.ui.screen.note_list

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denihilhamsyah.swiftnotes.R
import com.denihilhamsyah.swiftnotes.navigation.Screen
import com.denihilhamsyah.swiftnotes.ui.components.NoteItem


const val TAG = "NoteListScreen"

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController
) {
    val viewModel: NoteListViewModel = hiltViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    val isSelectItem = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateListOf<Int>() }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
                 AnimatedVisibility(
                     visible = isSelectItem.value,
                     enter = scaleIn(initialScale = 0.9f) + fadeIn(),
                     exit = scaleOut(targetScale = 0.9f) + fadeOut()
                 ) {
                     SelectTopBar(
                         onClickBack = {
                             isSelectItem.value = false
                             selectedItem.clear()
                         },
                         onClickDelete = {
                             viewModel.deleteNote(selectedItem)
                             isSelectItem.value = false
                         },
                         selectedItemSize = selectedItem.size
                     )
                 }
        },
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
                        onClick = { selectNote(
                            navController,
                            isSelectItem,
                            selectedItem,
                            id
                        ) },
                        onLongClick = { toggleNoteSelection(
                            selectedItem,
                            isSelectItem,
                            id
                        ) },
                    )
                }
            }
        } else {
            EmptyScreen()
        }
    }
}


@Composable
fun SelectTopBar(
    onClickBack: () -> Unit,
    onClickDelete: () -> Unit,
    selectedItemSize: Int
) {
   Surface(elevation = 8.dp) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .height(60.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               IconButton(onClick = onClickBack) {
                   Icon(
                       painter = painterResource(id = R.drawable.ic_close),
                       contentDescription = "back_button"
                   )
               }
               Text(
                   text = selectedItemSize.toString(),
                   style = MaterialTheme.typography.body1.copy(fontSize = 22.sp)
               )
           }

           IconButton(onClick = onClickDelete) {
               Icon(
                   painter = painterResource(id = R.drawable.ic_delete),
                   contentDescription = "delete_button"
               )
           }
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

private fun selectNote(
    navController: NavController,
    isSelectItem: MutableState<Boolean>,
    selectedItem: SnapshotStateList<Int>,
    id: Int
) {
    if (isSelectItem.value) {
        if (id in selectedItem) {
            selectedItem.remove(id)
            Log.d(TAG, "Item $id removed")
            if (selectedItem.size == 0) {
                isSelectItem.value = false
                Log.d(TAG, "Select item: ${isSelectItem.value}")
            }
        } else {
            selectedItem.add(id)
            Log.d(TAG, "Item $id added")
        }
    } else {
        navController.navigate(route = Screen.AddEditNote.setId(id))
    }
}

private fun toggleNoteSelection(
    selectedItem: SnapshotStateList<Int>,
    isSelectItem: MutableState<Boolean>,
    id: Int
) {
    if (id !in selectedItem) {
        selectedItem.add(id)
        Log.d(TAG, "Item $id added")

        isSelectItem.value = true
        Log.d(TAG, "Select item: ${isSelectItem.value}")
    }
}