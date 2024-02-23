package com.emmandroid.harrypotterapp.dashboard.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HarryPotterApiClientProvider {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provide(): HarryPotterApiClient =
        retrofit.create(HarryPotterApiClient::class.java)
}
