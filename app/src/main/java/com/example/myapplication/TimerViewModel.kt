package com.example.myapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

//  1 want one timer in a row, want infinite timers, and we want initialize only when viewed

//  2 want define 40 timers, each can start on initialization

//  3 ensure only viewed timers increment
class TimerViewModel : ViewModel() {
    // for step 2
    // create list of 40 integers starting 0

    // define our state variable for timer -- 0
    private val _timerSeconds = MutableStateFlow<Map<Int, MutableState<Long>>>(emptyMap())
    val timerSeconds = _timerSeconds

    // init -- when app starts, run the incrementTimer funciton


    // funciton to activate timer when index appears
    fun activateTimer(index: Int) {
        if (_timerSeconds.value.containsKey(index)) return
        val newState = _timerSeconds.value.toMutableMap()
        newState[index] = mutableStateOf(0L)
        _timerSeconds.value = newState
        startTimer(index)
    }

    // definite an method implentation to increment by one every se
    private fun startTimer(index: Int) {
        // when we call this function
        // increment timerSeconds every 1 second
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _timerSeconds.value[index]?.value = _timerSeconds.value[index]?.value?.plus(1) ?: 0L

            }
        }
    }
}