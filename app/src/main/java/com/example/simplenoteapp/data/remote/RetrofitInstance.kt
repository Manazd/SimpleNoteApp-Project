package com.example.simplenoteapp.data.remote

import com.example.simplenoteapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// A singleton object that creates and holds Retrofit instances for both AuthApi and NoteApi.

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val noteApi: NoteApi by lazy {
        retrofit.create(NoteApi::class.java)
    }
}