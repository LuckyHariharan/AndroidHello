package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PokemonScreen(viewModel: PokemonViewModel) {
    // Grab our state variables from our view model
    val pokemonList by viewModel.pokemonList.collectAsState()
    // lazy column or rows displaying all pokmeon and their url
    LazyColumn { items(pokemonList) { pokemon ->
        Row{
            Text(text = pokemon.name)
            Text(text = pokemon.url)
        }
    } }

}

