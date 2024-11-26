package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MainScreen(viewModel: MainViewModel) {
    // Observe the current image URL from the ViewModel
    val imageUrl by viewModel.imageUrl.collectAsState() // StateFlow is observed here

    // Combined Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Add padding around the screen
        verticalArrangement = Arrangement.SpaceBetween // Space out elements vertically
    ) {
        // Image Section
        AsyncImage(
            model = imageUrl, // Image URL from the ViewModel
            contentDescription = "Displayed Image",
            placeholder = painterResource(R.drawable.ic_launcher_background), // Placeholder image
            error = painterResource(R.drawable.ic_launcher_foreground), // Error image
            modifier = Modifier
                .fillMaxWidth() // Image fills the width
                .height(400.dp) // Fixed height for the image
        )

        // Comments Section
        var comment by remember { mutableStateOf("") } // Local state for comment input
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text("Add a comment") }, // Label for input field
            placeholder = { Text("Type your comment...") }, // Placeholder text
            modifier = Modifier
                .fillMaxWidth() // Input field spans the full width
                .padding(vertical = 16.dp) // Add vertical padding
        )

        // Action Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth() // Row spans the full width
                .padding(vertical = 8.dp), // Padding between rows
            horizontalArrangement = Arrangement.SpaceEvenly // Space out buttons evenly
        ) {
            // Favorite Button
            IconButton(onClick = { /* TODO: Add favorite logic */ }) {
                Icon(
                    imageVector = Icons.Default.Add, // Replace with your favorite icon
                    contentDescription = "Favorite"
                )
            }

            // Delete Button
            IconButton(onClick = { /* TODO: Add delete logic */ }) {
                Icon(
                    imageVector = Icons.Default.Delete, // Replace with your delete icon
                    contentDescription = "Delete"
                )
            }

            // Pass Button
            IconButton(onClick = { viewModel.fetchNewImage() }) { // Calls the ViewModel's fetchNewImage
                Icon(
                    imageVector = Icons.Default.PlayArrow, // Replace with your pass icon
                    contentDescription = "Pass"
                )
            }
        }
    }
}
