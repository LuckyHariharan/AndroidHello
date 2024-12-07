package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface InstrumentInterface {
    // setup get method
    @GET("instruments.json")
    suspend fun getInstruments(): Response<List<Instrument>>

    // include other methods as needed (GET, POST, UPDATE, DELETE?)
}