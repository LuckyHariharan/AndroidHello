package com.example.myapplication.instrument

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstrumentRepository {
    private val api: InstrumentInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(InstrumentInterface::class.java)
    }

    suspend fun fetchInstruments(): List<Instrument> {
        return api.getInstruments()
    }
}