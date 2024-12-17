package com.example.myapplication


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // VieModel -- 2 things 1 state variables, 2 method implementations
    // Declare your variables
    val listOfNames = mutableStateListOf("Curtis", "Lucky", "Vithushan")

    // State for toggling red or black (initial state: false means black)
    val listOfRedStates = mutableStateListOf(false, true, false)

    // Function to toggle the state of a specific index
    fun toggleColor(index: Int) {
        listOfRedStates[index] = !listOfRedStates[index]
    }
}
