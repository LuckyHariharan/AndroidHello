package com.example.myapplication

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {
    // create state variables using mutable state flow
    var _instruments = MutableStateFlow(emptyList<Instrument>())
    var instruments = _instruments.asStateFlow()
    var _filteredInstruments = MutableStateFlow(emptyList<Instrument>())
    var filteredInstruments = _filteredInstruments.asStateFlow()

    // init
    init {
        getInstruments()
    }

    // create getInstruments Method
    fun getInstruments(){
        // run coroutine
        viewModelScope.launch{
        // run try catch block
            try{
            val instrumentResponse = RetrofitInterface.retrofit.getInstruments()
                if(instrumentResponse.isSuccessful && instrumentResponse.body() != null){
                    _instruments.value = instrumentResponse.body()!!
                    _filteredInstruments.value = instrumentResponse.body()!!
                }
            }
            catch(e: Exception){
                Log.e("viewmodel getinstruments", "${e.message}")
            }
        }

    }

    // implement filter instruments
    fun filterInstruments(query: String){
        // filter each instrument list based on if the first few letters match the name
        val allInstruments = _instruments.value
        if(query==""){
            _filteredInstruments.value = allInstruments
        }
        else{
            _filteredInstruments.value = allInstruments.filter{
                instrument ->   instrument.name.length >= query.length && instrument.name.substring(0,query.length).equals(query, ignoreCase = true)
            }
        }
    }

}