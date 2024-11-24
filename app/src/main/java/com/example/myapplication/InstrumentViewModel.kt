package com.example.myapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InstrumentViewModel : ViewModel() {
    // state variables for _instrument
    // state variable for _filteredInstrument
    val _instruments = MutableLiveData<List<Instrument>>()
    val instruments = _instruments

    val _filteredInstruments = MutableLiveData<List<Instrument>>()
    val filteredInstruments = _filteredInstruments

    init{
        getInstruments()
    }

    // create getInstruments
    private fun getInstruments(){
        viewModelScope.launch{
            try{
                val response = RetrofitInstance.retrofitApi.getInstruments()
                if(response.isSuccessful && response.body()!=null){
                    _instruments.value = response.body()
                    _filteredInstruments.value = response.body()
                }
                else{
                    _instruments.value = emptyList()
                    _filteredInstruments.value = emptyList()
                }
            }
            catch (e: Exception){
                Log.e("error","${e.message}")
            }
        }
    }
    // in a coroutine
    // try catch block
    // val response ==
    // check if response is successful, and response.body() is not null
    // else if not succesful or null, set to emptyList()
    // update instrument, filtered instrument

    // catch Log.e

    // filter insturment method based on  instrument_type,
    // buttons, type
    fun filterInstruments(type: String){
        // if all then filtered instrumnt = instrument
        // otherwise, filter inxtruemtn based on type, update filtered instrument
        if(type == "all"){
            // filtered instruemnts is instruemtn
            _filteredInstruments.value = _instruments.value
        } else{
            _filteredInstruments.value = _instruments.value?.filter{ it.instrument_type == type }
        }

    }

}