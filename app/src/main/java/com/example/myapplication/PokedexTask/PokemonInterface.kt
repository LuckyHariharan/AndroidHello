package com.example.myapplication.PokedexTask

import retrofit2.Response
import retrofit2.http.GET

// Interface file to tell Retrofit the method needed
interface PokemonInterface{
     @GET("pokemon?limit=100000&offset=0")
     suspend fun getPokemon(): Response<PokemonResponse>
}
