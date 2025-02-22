package com.example.myapplication.PokedexTask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    // Store list of Pokémon
    private val _pokemonList = MutableLiveData<List<Result>>(emptyList())
    val pokemonList: LiveData<List<Result>> = _pokemonList

    // Store toggled state (Caught/Not Caught)
    private val _caughtPokemon = MutableLiveData<MutableSet<String>>(mutableSetOf())
    val caughtPokemon: MutableLiveData<MutableSet<String>> = _caughtPokemon

    // Init block to fetch Pokémon
    init {
        getPokemon()
    }

    // Fetch Pokémon using Retrofit
    fun getPokemon() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.retrofit.getPokemon()
                if (response.isSuccessful && response.body() != null) {
                    _pokemonList.value = response.body()!!.results // ✅ Extract only the list
                }
            } catch (e: Exception) {
                println("Error fetching Pokémon: ${e.message}")
            }
        }
    }

    // Toggle Pokémon caught status
    fun toggleCaughtPokemon(pokemon: Result) {
        val caughtSet = _caughtPokemon.value?.toMutableSet() ?: mutableSetOf()
        if (caughtSet.contains(pokemon.name)) {
            caughtSet.remove(pokemon.name)
        } else {
            caughtSet.add(pokemon.name)
        }
        _caughtPokemon.value = caughtSet
    }
}
