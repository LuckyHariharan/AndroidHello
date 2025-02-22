package com.example.myapplication.PokedexTask

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)