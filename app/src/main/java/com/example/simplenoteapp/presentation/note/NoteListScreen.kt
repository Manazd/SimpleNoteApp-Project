package com.example.simplenoteapp.presentation.note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplenoteapp.presentation.components.NoteCard

// list of notes in a grid.
// Supports search and navigation to details.

@Composable
fun NoteListScreen(nav: NavController, viewModel: NoteViewModel) {

    var search by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadNotes() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { nav.navigate("detail/0") }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        Column(Modifier.padding(padding).fillMaxSize()) {

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                    viewModel.loadNotes(it.takeIf { it.isNotBlank() })
                },
                label = { Text("Search…") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            when (uiState) {
                is NoteUiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }

                is NoteUiState.Error -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text((uiState as NoteUiState.Error).msg, color = MaterialTheme.colorScheme.error)
                }

                is NoteUiState.Success -> {
                    val notes = (uiState as NoteUiState.Success).notes
                    if (notes.isEmpty()) {
                        Box(Modifier.fillMaxSize(), Alignment.Center) {
                            Text("Start Your Journey", style = MaterialTheme.typography.headlineSmall)
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(notes) { note ->
                                NoteCard(note) {
                                    nav.navigate("detail/${note.id}")
                                }
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}
