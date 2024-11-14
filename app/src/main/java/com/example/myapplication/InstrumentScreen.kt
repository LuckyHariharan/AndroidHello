package com.example.myapplication

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    // Observe filtered instruments and set up filter options
    val instruments by viewModel.filteredInstruments.observeAsState(emptyList())
    val filters = listOf("All", "ETF", "Stock", "Crypto")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Filter Chips Row
        FilterChips(filters = filters, onFilterSelected = viewModel::filterInstruments)

        Spacer(modifier = Modifier.height(16.dp))

        // Instrument List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(instruments) { instrument ->
                InstrumentCard(instrument)
            }
        }
    }
}

@Composable
fun FilterChips(filters: List<String>, onFilterSelected: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        filters.forEach { filter ->
            OutlinedButton(
                onClick = { onFilterSelected(filter.lowercase()) },
                modifier = Modifier.weight(1f).padding(4.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Text(filter, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun InstrumentCard(instrument: Instrument) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Instrument Name and Ticker
            Text(
                text = "${instrument.ticker} - ${instrument.name}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Description
            Text(
                text = instrument.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(4.dp))
            // Prices
            Text("Current: $${instrument.current_price}", style = MaterialTheme.typography.bodySmall)
            Text("Previous: $${instrument.previous_price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
