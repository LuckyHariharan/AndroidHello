package com.example.myapplication.instrument

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// View Model to implement filter methods for instruments
class InstrumentViewModel : ViewModel() {
    private val repository = InstrumentRepository()
    private val _instruments = MutableLiveData<List<Instrument>>()
    val instruments: LiveData<List<Instrument>> get() = _instruments

    init {
        viewModelScope.launch {
            getInstruments()

        }
    }

    private suspend fun getInstruments() {
        val data = repository.fetchInstruments()
        _instruments.value = data
    }

}