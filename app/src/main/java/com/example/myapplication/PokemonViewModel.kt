package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// responsible for calling retrofit instance
// inside a courotine
// implement any methods

class PokemonViewModel : ViewModel() {
    // create state variables for our pokemon -- correctly collect mutable state for our screen
    private val _pokemonList = MutableStateFlow<List<Result>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    // define the method to recieve our pokemon data
    init {
        getPokemon()
    }
    private fun getPokemon() {
        // call our getPokemon method
        // call a courotine
        // inside here call our retrofit instance
        // if the response is not null, check the body response
        // if the response is valid (error check passes) then save the body
        // in our state variable
        viewModelScope.launch {
            val response = RetrofitInstance.retrofit.getPokemonList()
            if (response.isSuccessful && response.body() != null) {
                _pokemonList.value = response.body()!!.results
            }
        }

    }


    // any other methods defined below
}