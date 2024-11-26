package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit
        val apiService = RetrofitInstance.api
        // Create ViewModel with the API service
        val viewModel = MainViewModel(apiService)

        // Set the content to the MainScreen composable
        setContent {
<<<<<<< Updated upstream
            MainScreen(viewModel)
=======
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InstrumentScreen(viewModel = InstrumentViewModel())
                }
            }
>>>>>>> Stashed changes
        }
    }
}
