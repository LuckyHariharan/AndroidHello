package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(viewModel: MainViewModel) {
    // Display the UI
    Row {
        // Iterate over names and their current color states
        viewModel.listOfNames.forEachIndexed { index, name ->
            val isRed = viewModel.listOfRedStates[index]
            Text(
                text = name,
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = Color.Green)
                    .clickable {
                        viewModel.toggleColor(index) // Toggle the color on click
                    },
                color = if (isRed) Color.Red else Color.Black
            )
        }
    }
}
