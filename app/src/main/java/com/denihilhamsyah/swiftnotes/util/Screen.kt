package com.denihilhamsyah.swiftnotes.util

sealed class Screen(val route: String) {
    object NoteList: Screen(route = "list_note_screen")
    object AddEditNote: Screen(route = "add_edit_note_screen")
}