package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun getPokemonList(): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemonAbilities(@Path("name") pokemonName:String): Response<PokemonAbilitiesList>
}