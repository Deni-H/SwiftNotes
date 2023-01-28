package com.denihilhamsyah.swiftnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.denihilhamsyah.swiftnotes.ui.screen.note_list.NoteListScreen
import com.denihilhamsyah.swiftnotes.ui.theme.SwiftNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwiftNotesTheme {
                NoteListScreen()
            }
        }
    }
}