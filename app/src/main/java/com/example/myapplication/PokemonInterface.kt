package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface PokemonInterface {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getPokemonList(): Response<PokemonList>
}