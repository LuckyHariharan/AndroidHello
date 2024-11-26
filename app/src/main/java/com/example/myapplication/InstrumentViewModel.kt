package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {

    private val _instruments = MutableLiveData<List<Instrument>>()
    val instruments: LiveData<List<Instrument>> = _instruments

    private val _filteredInstruments = MutableLiveData<List<Instrument>>()
    val filteredInstruments: LiveData<List<Instrument>> = _filteredInstruments

    init {
        getInstruments()
    }

    fun getInstruments() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getInstruments()
                if (response.isSuccessful && response.body() != null) {
                    _instruments.value = response.body()
                    _filteredInstruments.value = response.body() // Initialize filtered list
                } else {
                    _instruments.value = emptyList()
                    _filteredInstruments.value = emptyList()
                }
            } catch (e: Exception) {
                _instruments.value = emptyList()
                _filteredInstruments.value = emptyList()
            }
        }
    }

    fun filterInstruments(type: String) {
        _filteredInstruments.value = if (type == "All") {
            _instruments.value
        } else {
            _instruments.value?.filter { it.instrument_type == type }
        }
    }
}
