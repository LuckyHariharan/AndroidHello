package com.example.myapplication

import InstrumentsItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel = viewModel()) {
    // Observing filtered instruments from ViewModel
    val instruments by viewModel.filteredInstruments.observeAsState(emptyList())
    // State for the selected filter
    var selectedFilter by remember { mutableStateOf("All") }

    // Filter chips: All, Stock, ETF, Crypto
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // All Instruments
            FilterChip(
                label = "All",
                isSelected = selectedFilter == "All",
                onClick = {
                    selectedFilter = "All"
                    viewModel.filterInstruments("All") // Display all instruments
                }
            )
            // Stock Instruments
            FilterChip(
                label = "Stock",
                isSelected = selectedFilter == "stock",
                onClick = {
                    selectedFilter = "stock"
                    viewModel.filterInstruments("stock")
                }
            )
            // ETF Instruments
            FilterChip(
                label = "ETF",
                isSelected = selectedFilter == "etf",
                onClick = {
                    selectedFilter = "etf"
                    viewModel.filterInstruments("etf")
                }
            )
            // Crypto Instruments
            FilterChip(
                label = "Crypto",
                isSelected = selectedFilter == "crypto",
                onClick = {
                    selectedFilter = "crypto"
                    viewModel.filterInstruments("crypto")
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the filtered instruments in a LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(instruments) { instrument ->
                InstrumentItem(instrument = instrument)
            }
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(label)
    }
}

@Composable
fun InstrumentItem(instrument: InstrumentsItem) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = instrument.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Ticker: ${instrument.ticker}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Type: ${instrument.instrument_type}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Current Price: $${instrument.current_price}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Previous Price: $${instrument.previous_price}", style = MaterialTheme.typography.bodySmall)
            Text(text = instrument.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}
