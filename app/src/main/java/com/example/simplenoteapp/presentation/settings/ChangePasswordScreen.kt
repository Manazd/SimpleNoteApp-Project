package com.example.simplenoteapp.presentation.settings

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplenoteapp.data.remote.RetrofitInstance
import com.example.simplenoteapp.utils.TokenManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(navController: NavController, app: Application) {
    var current by rememberSaveable { mutableStateOf("") }
    var newPass by rememberSaveable { mutableStateOf("") }
    var confirmPass by rememberSaveable { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Change Password") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Please input your current password first", color = MaterialTheme.colorScheme.primary)

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = current,
                onValueChange = { current = it },
                label = { Text("Current Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = newPass,
                onValueChange = { newPass = it },
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text("Retype New Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            if (errorText.isNotBlank()) {
                Text(errorText, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    if (newPass != confirmPass) {
                        errorText = "Passwords do not match"
                        return@Button
                    }

                    coroutineScope.launch {
                        try {
                            val token = TokenManager.getToken(app) ?: ""
                            RetrofitInstance.authApi.changePassword(
                                "Bearer $token",
                                mapOf(
                                    "old_password" to current,
                                    "new_password" to newPass,
                                    "confirm_password" to confirmPass
                                )
                            )
                            navController.popBackStack()
                        } catch (e: Exception) {
                            errorText = e.message ?: "Failed to change password"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit New Password →")
            }
        }
    }
}
