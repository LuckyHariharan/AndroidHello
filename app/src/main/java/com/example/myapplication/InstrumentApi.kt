package com.example.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object InstrumentApi {
    // setup base url constant
    private const val BASE_URL =
        "https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/"

    // create retrofit instance and parse using GSON into data objects
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    // create and setup retrofit interface
    val instrumentService: InstrumentApi = retrofit.create(InstrumentApi::class.java)

    // define the InstrumentApi interface
    interface InstrumentApi {
        @GET("instruments.json")
        suspend fun getInstruments(): Response<List<Instrument>>
    }
}
