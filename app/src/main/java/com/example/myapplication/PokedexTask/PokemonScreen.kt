package com.example.myapplication.PokedexTask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonScreen(viewModel: PokemonViewModel = viewModel()) {
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    val caughtPokemon by viewModel.caughtPokemon.observeAsState(emptySet())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Pokédex", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon, caughtPokemon.contains(pokemon.name)) {
                    viewModel.toggleCaughtPokemon(pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Result, isCaught: Boolean, onToggle: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onToggle() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = pokemon.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = if (isCaught) "Caught ✅" else "Not Caught ❌", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
