package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface InstrumentInterface {
    // creating a get method for out instruments
    @GET("instruments.json")
    suspend fun getInstruments(): Response<List<Instrument>>

}