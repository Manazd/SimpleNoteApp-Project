package com.example.simplenoteapp.domain.repository

import android.content.Context
import com.example.simplenoteapp.data.remote.RetrofitInstance
import com.example.simplenoteapp.data.remote.model.NoteDto
import com.example.simplenoteapp.domain.model.Note
import com.example.simplenoteapp.utils.TokenManager

class NoteRepository(private val context: Context) {

    private fun dtoToDomain(dto: NoteDto) = Note(
        id = dto.id,
        title = dto.title,
        content = dto.content,
        timestamp = dto.timestamp
    )

    suspend fun fetchNotes(search: String? = null, page: Int? = null): List<Note> {
        val token = TokenManager.getToken(context) ?: ""
        val bearer = "Bearer $token"
        return RetrofitInstance.noteApi
            .getNotes(bearer, search, page)
            .map(::dtoToDomain)
    }

    suspend fun createNote(title: String, content: String): Note {
        val token = TokenManager.getToken(context) ?: ""
        val dto = NoteDto(0, title, content, "")
        return dtoToDomain(RetrofitInstance.noteApi.addNote("Bearer $token", dto))
    }

    suspend fun updateNote(id: Int, title: String, content: String): Note {
        val token = TokenManager.getToken(context) ?: ""
        val dto = NoteDto(id, title, content, "")
        return dtoToDomain(RetrofitInstance.noteApi.updateNote("Bearer $token", id, dto))
    }

    suspend fun deleteNote(id: Int) {
        val token = TokenManager.getToken(context) ?: ""
        RetrofitInstance.noteApi.deleteNote("Bearer $token", id)
    }
}
