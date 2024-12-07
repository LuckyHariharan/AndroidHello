package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.RetrofitInterface.retrofitApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {

    // setup state variables -- state variables tradeoffs (state flow, live data)
    var _instrumentList = MutableStateFlow<List<Instrument>>(emptyList())
    var instrumentList = _instrumentList.asStateFlow()
    // setup state variables -- state variables tradeoffs (state flow, live data)
    var _filteredInstrumentList = MutableStateFlow<List<Instrument>>(emptyList())
    var filteredInstrumentList = _filteredInstrumentList.asStateFlow()

    // setup init with getInstruments
    init {
        getInstruments()
    }

    // use coroutine to supply/build getInstruments method
    // and save response body (if its non-null/exists) in state
    private fun getInstruments(){
        // launch a co routine
        viewModelScope.launch {
            try{
                val instrumentResponse = retrofitApi.getInstruments()
                if(instrumentResponse.isSuccessful && instrumentResponse.body() != null){
                    _instrumentList.value = instrumentResponse.body()!!
                    _filteredInstrumentList.value = instrumentResponse.body()!!
                }
            }
            catch (e: Exception){
                Log.e("viewModel Error","${e.message}")
            }
        }
    }

    // implement filter methods
    fun filterInstruments(type: String){
        if(type == "all"){
            _filteredInstrumentList.value = _instrumentList.value
        }
        else{
           val newList = _instrumentList.value.filter{instrument -> instrument.instrument_type == type}
            _filteredInstrumentList.value = newList
        }
    }
}