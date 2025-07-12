package com.example.simplenoteapp.data.remote

import com.example.simplenoteapp.data.remote.model.NoteDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface NoteApi {
    @GET("notes/")
    suspend fun getNotes(
        @Header("Authorization") token: String,
        @Query("search") search: String? = null,
        @Query("page")   page: Int?   = null
    ): List<NoteDto>

    @POST("notes/")
    suspend fun addNote(@Header("Authorization") token: String,
                        @Body note: NoteDto): NoteDto

    @PUT("notes/{id}/")
    suspend fun updateNote(@Header("Authorization") token: String,
                           @Path("id") id: Int,
                           @Body note: NoteDto): NoteDto

    @DELETE("notes/{id}/")
    suspend fun deleteNote(@Header("Authorization") token: String,
                           @Path("id") id: Int)

}
