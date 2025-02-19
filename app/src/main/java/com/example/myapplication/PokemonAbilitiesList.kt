package com.example.myapplication


data class PokemonAbilitiesList(
    val abilities: List<AbilitySlot> // Fix: Use AbilitySlot instead of Ability
)

data class AbilitySlot( // This represents each ability slot in the JSON response
    val ability: AbilityX, // Fix: This should map to AbilityX
    val is_hidden: Boolean,
    val slot: Int
)
