package com.example.simplenoteapp.presentation.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenoteapp.domain.model.Note
import com.example.simplenoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class NoteUiState {
    object Idle : NoteUiState()
    object Loading : NoteUiState()
    data class Success(val notes: List<Note>) : NoteUiState()
    data class Error(val msg: String) : NoteUiState()
}

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = NoteRepository(app.applicationContext)

    private val _state = MutableStateFlow<NoteUiState>(NoteUiState.Idle)
    val state: StateFlow<NoteUiState> = _state

    fun loadNotes(search: String? = null) {
        viewModelScope.launch {
            _state.value = NoteUiState.Loading
            try {
                val list = repo.fetchNotes(search)
                _state.value = NoteUiState.Success(list)
            } catch (e: Exception) {
                _state.value = NoteUiState.Error(e.message ?: "Unable to load notes")
            }
        }
    }

    fun deleteNote(id: Int) = viewModelScope.launch {
        repo.deleteNote(id)
        loadNotes()
    }

    fun createNote(title: String, content: String) = viewModelScope.launch {
        repo.createNote(title, content)
        loadNotes()
    }
    fun updateNote(id: Int, title: String, content: String) = viewModelScope.launch {
        repo.updateNote(id, title, content)
        loadNotes()
    }
}
