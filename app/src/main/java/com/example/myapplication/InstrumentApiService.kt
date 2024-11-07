package com.example.myapplication

import Instruments
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object InstrumentApi {
    // Set up BASE_URL
    private const val BASE_URL =
        "https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/"

    // setup retrofit builder and GSON Converter
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    // Setup API Service
    val service: ApiService = retrofit.create(ApiService::class.java)

    // Define API Interface
    interface ApiService {
        @GET("instruments.json")
        suspend fun getInstruments(): Response<Instruments>
    }

}
