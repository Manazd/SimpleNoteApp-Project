package com.example.simplenoteapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.simplenoteapp.navigation.NavGraph

@Composable
fun MainScreen(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", Icons.Default.Home),
        BottomNavItem("add_note", Icons.Default.Add),
        BottomNavItem("settings", Icons.Default.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.route) },
                        selected = currentRoute == item.route,
                        onClick = {
                            // Special handling for add_note
                            if (item.route == "add_note") {
                                navController.navigate("detail/0")
                            } else {
                                navController.navigate(item.route) {
                                    popUpTo("home") { inclusive = false }
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

data class BottomNavItem(val route: String, val icon: ImageVector)
