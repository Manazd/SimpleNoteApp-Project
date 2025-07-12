package com.example.simplenoteapp.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplenoteapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Handle login and register logic using AuthRepository.

class AuthViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = AuthRepository(app.applicationContext)

    private val _authState = MutableStateFlow<AuthenticationState>(AuthenticationState.Idle)
    val authState: StateFlow<AuthenticationState> = _authState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthenticationState.Loading
            try {
                val response = repository.login(email, password)
                _authState.value = AuthenticationState.Success(response.access)
            } catch (e: Exception) {
                _authState.value = AuthenticationState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch {
            _authState.value = AuthenticationState.Loading
            try {
                val response = repository.register(email, password, username, firstName, lastName)
                _authState.value = AuthenticationState.Success(response.access)
            } catch (e: Exception) {
                _authState.value = AuthenticationState.Error(e.message ?: "Registration failed")
            }
        }
    }
}
