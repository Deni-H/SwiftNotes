package com.denihilhamsyah.swiftnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.denihilhamsyah.swiftnotes.ui.theme.SwiftNotesTheme
import com.denihilhamsyah.swiftnotes.util.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwiftNotesTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}