package com.example.simplenoteapp.data.remote.model

// Data Transfer Object
data class NoteDto(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: String
)
