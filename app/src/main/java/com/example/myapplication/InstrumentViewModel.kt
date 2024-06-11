package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InstrumentViewModel : ViewModel() {
    // initialize list of instruments as a private variable
    // declare list of instruments as public variable, using mutable state flow
    private val _instruments = MutableStateFlow<List<Instrument>>(emptyList())
    val instruments: StateFlow<List<Instrument>> get() = _instruments.asStateFlow()

    private val _filteredInstruments = MutableStateFlow<List<Instrument>>(emptyList())
    val filteredInstruments: StateFlow<List<Instrument>> get() = _filteredInstruments.asStateFlow()

    // Initialize with all types to ensure they are checked by default
    private val selectedTypes = mutableSetOf("etf", "stock", "crypto")

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
            emptyList() // Show no instruments if no types are selected
        } else {
            _instruments.value.filter { selectedTypes.contains(it.instrument_type) }
        }
    }

    // Function to check if a type is selected
    fun isTypeSelected(type: String): Boolean {
        return selectedTypes.contains(type)
    }
}
