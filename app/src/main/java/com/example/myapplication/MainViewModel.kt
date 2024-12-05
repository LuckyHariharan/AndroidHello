package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(private val apiService: ApiService) : ViewModel() {

    private val _imageUrl = MutableStateFlow("https://fastly.picsum.photos/id/585/2000/3000.jpg?hmac=NMrFbER7WEvOQxpvfiEkK9sXO2aEm20FOPN872XK4HI") // Default image URL
    val imageUrl: StateFlow<String> get() = _imageUrl

    init {
        fetchNewImage() // Fetch an initial image when the ViewModel is created
    }

    fun fetchNewImage() {
        viewModelScope.launch {
            try {
                val newImage = apiService.getRandomImage() // Call the API
                _imageUrl.value = newImage // Update the state
            } catch (e: Exception) {
                _imageUrl.value = "https://fastly.picsum.photos/id/585/2000/3000.jpg?hmac=NMrFbER7WEvOQxpvfiEkK9sXO2aEm20FOPN872XK4HI" // Handle errors
            }
        }
    }
}
