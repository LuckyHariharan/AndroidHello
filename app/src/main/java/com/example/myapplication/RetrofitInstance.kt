package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // setup Base URl
    private const val BASE_URL="https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/"
    // create retrofit buildiner
    val retrofit: InstrumentApi = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(
        InstrumentApi::class.java)
}