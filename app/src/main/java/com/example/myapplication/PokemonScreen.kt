package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonScreen(viewModel: PokemonViewModel) {
    val pokemonList by viewModel.pokemonList.collectAsState()
    val pokemonAbilities by viewModel.pokemonAbilities.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pokemonList) { pokemon ->
            var showAbilities by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Pokémon Name
                Text(text = pokemon.name, modifier = Modifier.padding(bottom = 4.dp))

                // Pokémon URL
                Text(text = pokemon.url, modifier = Modifier.padding(bottom = 8.dp))

                // Button + Abilities Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Show Abilities Button
                    Button(onClick = {
                        viewModel.getPokemonAbilities(pokemon.name)
                        showAbilities = !showAbilities
                    }) {
                        Text("Show Abilities")
                    }

                    // Abilities appear to the right when clicked
                    if (showAbilities) {
                        Column {
                            pokemonAbilities.forEach { ability ->
                                Text(text = ability)
                            }
                        }
                    }
                }
            }
        }
    }
}