package com.example.myapplication

data class PokemonList(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)