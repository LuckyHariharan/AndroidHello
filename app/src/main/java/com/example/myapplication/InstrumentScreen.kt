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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    val instruments by viewModel.filteredInstruments.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Filter buttons
        FilterButtons(
            filters = listOf("All", "Stock", "Crypto", "ETF"),
            onFilterClick = { viewModel.filterInstruments(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn for displaying instruments
        LazyColumn {
            items(instruments.size) { index ->
                InstrumentCard(instrument = instruments[index])
            }
        }
    }
}

@Composable
fun FilterButtons(filters: List<String>, onFilterClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        filters.forEach { filter ->
            OutlinedButton(onClick = { onFilterClick(filter) }) {
                Text(text = filter)
            }
        }
    }
}

@Composable
fun InstrumentCard(instrument: Instrument) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp), color) {
        Text(text = "Name: ${instrument.name} (${instrument.ticker})")
        Text(text = "Type: ${instrument.instrument_type}")
        Text(text = "Price: \$${instrument.current_price}")
        Text(text = "Previous Price: \$${instrument.previous_price}")
        Text(text = "Description: ${instrument.description}")
    }
}
