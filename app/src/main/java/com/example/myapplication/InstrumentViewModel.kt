package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {
    // initialize instrument list - mutable live data
    val _instruments = MutableLiveData<List<Instrument>>()
    val instruments: LiveData<List<Instrument>> = _instruments

    // initialize filtered instrument list
    val _filteredInstruments = MutableLiveData<List<Instrument>>()
    val filteredInstruments: LiveData<List<Instrument>> = _filteredInstruments

    // init call getInstruments retrofit
    init {
        getInstruments()
    }

    // Function to getInstruments from the API
    private fun getInstruments() {
        viewModelScope.launch {
            try {
                // Make the API Call using retrofit service
                val response = InstrumentApi.instrumentService.getInstruments()
                // check if response was succesful
                if (response.isSuccessful) {
                    val instruments = response.body()
                    // if not null, update the live data 
                    if (instruments != null) {
                        _instruments.postValue(instruments)
                        _filteredInstruments.postValue(instruments)
                    }
                    else {
                        Log.e("InstrumentViewModel", "Exception: ${response.errorBody()}")
                    }
                }
            }
            catch(e: Exception){
                Log.e("InstrumentViewModel", "Exception ${e.message}")
            }
        }
    }

    fun filterInstruments(type: String) {
        _instruments.value?.let { instruments ->
            _filteredInstruments.value = if (type == "All") {
                instruments
            } else {
                instruments.filter { it.instrument_type == type }
            }
        }
    }
}