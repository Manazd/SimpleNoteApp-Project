package com.example.simplenoteapp.navigation

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simplenoteapp.presentation.auth.AuthViewModel
import com.example.simplenoteapp.presentation.auth.LoginScreen
import com.example.simplenoteapp.presentation.auth.RegisterScreen
import com.example.simplenoteapp.presentation.note.NoteDetailScreen
import com.example.simplenoteapp.presentation.note.NoteListScreen
import com.example.simplenoteapp.presentation.note.NoteViewModel
import com.example.simplenoteapp.presentation.settings.ChangePasswordScreen
import com.example.simplenoteapp.presentation.settings.SettingsScreen

// navigation graph between all screens (login, register, home, detail, settings).

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    val authVm = AuthViewModel(ctx.applicationContext as Application)
    val noteVm = NoteViewModel(ctx.applicationContext as Application)

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login")    { LoginScreen(navController, authVm) }
        composable("register") { RegisterScreen(navController, authVm) }

        composable("home")     { NoteListScreen(navController, noteVm) }

        composable("detail/{noteId}") { backEntry ->
            val id = backEntry.arguments?.getString("noteId")?.toIntOrNull() ?: 0
            NoteDetailScreen(navController, id, noteVm)
        }

        composable("settings") {
            SettingsScreen(navController, ctx.applicationContext as Application)
        }

        composable("change_password") {
            ChangePasswordScreen(navController, ctx.applicationContext as Application)
        }
    }
}

