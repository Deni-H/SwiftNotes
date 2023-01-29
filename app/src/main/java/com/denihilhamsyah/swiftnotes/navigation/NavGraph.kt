package com.denihilhamsyah.swiftnotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denihilhamsyah.swiftnotes.ui.screen.add_edit_note.AddEditScreen
import com.denihilhamsyah.swiftnotes.ui.screen.note_list.NoteListScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NoteList.route
    ) {
        composable(
            route = Screen.NoteList.route
        ) {
            NoteListScreen(navController)
        }
        composable(
            route = Screen.AddEditNote.route
        ) {
            AddEditScreen(navController)
        }
    }
}