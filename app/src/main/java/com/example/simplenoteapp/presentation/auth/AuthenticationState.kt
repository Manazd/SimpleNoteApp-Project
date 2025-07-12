package com.example.simplenoteapp.presentation.auth

sealed class AuthenticationState {
    data object Idle : AuthenticationState()
    data object Loading : AuthenticationState()
    data class Success(val token: String) : AuthenticationState()
    data class Error(val message: String) : AuthenticationState()
}
