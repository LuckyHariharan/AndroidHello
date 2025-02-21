package com.example.myapplication.InstrumentFeature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {
    // create state variables
    var _instruments = MutableStateFlow<List<InstrumentItem>>(emptyList<InstrumentItem>())
    var instruments = _instruments.asStateFlow()

    var _filteredInstruments = MutableStateFlow<List<InstrumentItem>>(emptyList<InstrumentItem>())
    var filteredInstruments = _filteredInstruments.asStateFlow()


    // init with get method
    init {
        getInstruments()
    }

    // create get method
    fun getInstruments() {

        // call courotine
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.retrofit.getInstruments()
                if (response.isSuccessful && response.body() != null) {
                    _instruments.value = response.body()!!
                    _filteredInstruments.value = response.body()!!

                }
            } catch (e: Exception) {
                Log.d("viewmodel coroutine exception", "$e")
            }
        }

    }

    // implement search for text field
    fun filterInstruments(query: String) {
        if (query == "") {
            _filteredInstruments.value = _instruments.value
        } else {
            val allData = _instruments.value
            _filteredInstruments.value = allData.filter { instrument ->
                instrument.name.length >= query.length && instrument.name.substring(0, query.length)
                    .equals(query, ignoreCase = true)
            }
        }
    }
}