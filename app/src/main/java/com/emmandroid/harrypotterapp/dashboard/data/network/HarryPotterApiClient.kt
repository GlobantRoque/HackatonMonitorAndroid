package com.emmandroid.harrypotterapp.dashboard.data.network

import com.emmandroid.harrypotterapp.dashboard.data.model.BookCharacter
import com.emmandroid.harrypotterapp.dashboard.data.model.SpellDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApiClient {

    @GET("characters")
    suspend fun getCharacters(): Response<List<BookCharacter>>

    @GET("character/{characterId}")
    suspend fun getCharacterById(@Path("characterId")characterId: String): Response<List<BookCharacter>>

    @GET("characters/students")
    suspend fun getStudents(): Response<List<BookCharacter>>

    @GET("characters/staff")
    suspend fun getStaff(): Response<List<BookCharacter>>

    @GET("characters/house/gryffindor")
    suspend fun getCharactersByHouse(): Response<BookCharacter> // Response<List<BookCharacter>>

    @GET("spells")
    suspend fun getAllSpells(): Response<List<SpellDataModel>>
}
