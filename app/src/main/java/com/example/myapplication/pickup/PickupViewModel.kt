package com.example.myapplication.Pickup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PickupViewModel : ViewModel() {

    // LiveData to hold the next pickup date
    private val _nextPickupDate = MutableLiveData<String>().apply {
        value = "2024-10-21 10:00" // Hardcoded date
    }
    val nextPickupDate: LiveData<String> = _nextPickupDate

}
