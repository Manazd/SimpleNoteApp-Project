package com.example.simplenoteapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplenoteapp.domain.model.Note

@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4)),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(note.title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(
                note.content.take(120) + if (note.content.length > 120) "…" else "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
