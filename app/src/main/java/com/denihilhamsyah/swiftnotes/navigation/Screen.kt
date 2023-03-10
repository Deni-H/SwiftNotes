package com.denihilhamsyah.swiftnotes.navigation

sealed class Screen(val route: String) {
    object NoteList: Screen(route = "list_note_screen")
    object AddEditNote: Screen(route = "add_edit_note_screen?id={id}") {
        fun setId(id: Int = -1): String {
            return "add_edit_note_screen?id=$id"
        }
    }
}