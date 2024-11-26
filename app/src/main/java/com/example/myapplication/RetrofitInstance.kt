package com.example.myapplication

<<<<<<< Updated upstream

import okhttp3.OkHttpClient
=======
>>>>>>> Stashed changes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
<<<<<<< Updated upstream
    private const val BASE_URL = "https://picsum.photos/"

    // Configure OkHttpClient to follow redirects
    private val client = OkHttpClient.Builder()
        .followRedirects(true) // Ensure redirects are followed
        .followSslRedirects(true) // Follow HTTPS redirects as well
        .build()

    // Create the Retrofit API service
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Use the configured client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
=======
    private const val BASE_URL =
        "https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/"

    val apiService: InstrumentInterface by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(InstrumentInterface::class.java)
>>>>>>> Stashed changes
    }
}
