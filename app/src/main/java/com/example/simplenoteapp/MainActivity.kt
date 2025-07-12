package com.example.simplenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraph
import androidx.navigation.compose.rememberNavController
import com.example.simplenoteapp.ui.theme.SimpleNoteTheme
import com.example.simplenoteapp.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleNoteTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
