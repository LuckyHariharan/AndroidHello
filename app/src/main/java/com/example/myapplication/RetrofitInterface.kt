package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// setup singleton object to handle retrofit instance

object RetrofitInterface{
    // setup base url
    private const val BASE_URL = "https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/"

    // setup retrofit builder
     val retrofitApi = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(InstrumentInterface::class.java)


}