package com.example.simplenoteapp.domain.repository

import android.content.Context
import com.example.simplenoteapp.data.remote.RetrofitInstance
import com.example.simplenoteapp.data.remote.model.AuthRequest
import com.example.simplenoteapp.data.remote.model.AuthResponse
import com.example.simplenoteapp.utils.TokenManager

class AuthRepository(private val context: Context) {

    // login request to backend
    suspend fun login(email: String, password: String): AuthResponse {
        // call backend with authApi
        val response = RetrofitInstance.authApi.login(
            AuthRequest(email = email, password = password)
        )
        TokenManager.saveToken(context, response.access)
        return response
    }

    suspend fun register(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String
    ): AuthResponse {
        val response = RetrofitInstance.authApi.register(
            AuthRequest(
                email = email,
                password = password,
                username = username,
                firstName = firstName,
                lastName = lastName
            )
        )
        TokenManager.saveToken(context, response.access)
        return response
    }

    suspend fun logout() {
        TokenManager.saveToken(context, "")
    }
}
