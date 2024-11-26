package com.example.myapplication


import retrofit2.http.GET

/**
 * ApiService - Retrofit interface for fetching random images.
 */
interface ApiService {
    // Endpoint for fetching random images
    @GET("2000/3000")
    suspend fun getRandomImage(): String
}
