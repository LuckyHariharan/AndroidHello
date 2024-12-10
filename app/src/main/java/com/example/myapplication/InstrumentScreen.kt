package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    val instruments by viewModel.filteredInstruments.collectAsState(initial = emptyList<Instrument>())
    var query by remember { mutableStateOf("") }
    // Column Layout
    Column {
        // Text Search Field

        OutlinedTextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
                viewModel.filterInstruments(query) // Trigger filtering
            },
            label = { Text("Search Instruments") }
        )
        Spacer(modifier = Modifier.size(12.dp))

        // lazy column to efficiently recycle instruments

        LazyColumn(modifier=Modifier.fillMaxWidth()){
            items(instruments) { instrument ->
                Text(text = instrument.name)
            }
        }

    }
}