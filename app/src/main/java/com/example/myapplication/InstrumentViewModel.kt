package com.example.myapplication

import InstrumentsItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {

    // State variable to hold the full list of instruments
    private val _instrumentList = MutableLiveData<List<InstrumentsItem>>()
    val instrumentList: LiveData<List<InstrumentsItem>> = _instrumentList

    // State variable to hold the filtered list of instruments
    private val _filteredInstruments = MutableLiveData<List<InstrumentsItem>>()
    val filteredInstruments: LiveData<List<InstrumentsItem>> = _filteredInstruments

    // Initialize by fetching instrument data
    init {
        getInstruments()
    }

    private fun getInstruments() {
        viewModelScope.launch {
            try {
                // Make the API call
                val response = InstrumentApi.service.getInstruments()
                if (response.isSuccessful) {
                    // Save the response to _instrumentList
                    val instruments = response.body()
                    if (instruments != null) {
                        _instrumentList.postValue(instruments)
                        _filteredInstruments.postValue(instruments) // Initialize the filtered list with all instruments
                    }
                    Log.d("InstrumentViewModel", "Fetched instruments: $instruments")
                } else {
                    Log.e("InstrumentViewModel", "Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("InstrumentViewModel", "Exception: ${e.message}")
            }
        }
    }

    // Function to filter instruments by type
    fun filterInstruments(type: String) {
        _instrumentList.value?.let { instruments ->
            // If "All" is selected, show the full list
            if (type == "All") {
                _filteredInstruments.value = instruments
            } else {
                // Filter the list based on the specified type
                val filteredList = instruments.filter { it.instrument_type == type }
                _filteredInstruments.value = filteredList
            }
        }
    }
}