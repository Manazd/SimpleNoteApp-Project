package com.example.simplenoteapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val serverId: Int?, // ID from backend (nullable for unsynced notes)
    val title: String,
    val content: String,
    val timestamp: String
)
