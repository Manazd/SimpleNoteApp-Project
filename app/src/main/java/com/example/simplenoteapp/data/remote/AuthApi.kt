package com.example.simplenoteapp.data.remote

import com.example.simplenoteapp.data.remote.model.AuthRequest
import com.example.simplenoteapp.data.remote.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login/")
    suspend fun login(@Body request: AuthRequest): AuthResponse

    @POST("auth/register/")
    suspend fun register(@Body request: AuthRequest): AuthResponse

    @PATCH("auth/change-password/")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body body: Map<String, String>
    )
}
