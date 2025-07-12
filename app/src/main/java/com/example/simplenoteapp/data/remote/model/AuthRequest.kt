package com.example.simplenoteapp.data.remote.model

data class AuthRequest(
    val email: String,
    val password: String,
    val username: String? = null,
    val firstName: String? = null,
    val lastName: String? = null
)