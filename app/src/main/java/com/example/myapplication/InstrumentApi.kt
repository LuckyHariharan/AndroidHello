package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface InstrumentApi {

    @GET("instruments.json")
    suspend fun getInstruments(): Response<List<Instrument>>
}