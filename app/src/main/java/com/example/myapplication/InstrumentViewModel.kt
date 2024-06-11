package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {
    // initialize list of instruments as a private variable
    // declare list of instruments as public variable, using mutable state flow
    private val _instruments = MutableStateFlow<List<Instrument>>(emptyList())
    val instruments: MutableStateFlow<List<Instrument>> get() = _instruments

    private val _filteredInstruments = MutableStateFlow<List<Instrument>>(emptyList())
    val filteredInstruments: StateFlow<List<Instrument>> get() = _filteredInstruments.asStateFlow()

    private val selectedTypes = mutableSetOf<String>()
    // Update Instruments using financial instrument data
    fun updateInstruments(newInstruments: List<Instrument>) {
        _instruments.value = newInstruments
        filterInstruments()
    }
    // Function to toggle the selection state of an instrument type
    fun toggleInstrumentType(type: String) {
        if (selectedTypes.contains(type)) {
            selectedTypes.remove(type)
        } else {
            selectedTypes.add(type)
        }
        filterInstruments()
    }

    // Implement Filter by Instrumemnt Type, stock, crypto, etf, and all
    private fun filterInstruments() {
        _filteredInstruments.value = if (selectedTypes.isEmpty()) {
            _instruments.value
        } else {
            _instruments.value.filter { selectedTypes.contains(it.instrument_type) }
        }
    }

}