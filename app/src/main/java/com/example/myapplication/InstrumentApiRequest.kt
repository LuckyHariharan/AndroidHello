package com.example.myapplication

import retrofit2.http.GET

interface InstrumentApiRequest{
    @GET("https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/instruments.json")
    suspend fun getInstrumentData(): List<InstrumentItem>
}