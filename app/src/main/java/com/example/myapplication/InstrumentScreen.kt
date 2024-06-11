package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    val instruments by viewModel.filteredInstruments.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        FilterComposable(viewModel)
        LazyColumn {
            items(instruments) { instrument ->
                InstrumentItemView(instrument)
            }
        }
    }
}

@Composable
fun FilterComposable(viewModel: InstrumentViewModel) {
    val instrumentTypes = listOf("stock", "etf", "crypto")

    Column {
        Text("Filter by Instrument Type:")
        instrumentTypes.forEach { type ->
            FilterItem(type, viewModel)
        }
    }
}

@Composable
fun FilterItem(type: String, viewModel: InstrumentViewModel) {
    var checkedState by remember { mutableStateOf(viewModel.isTypeSelected(type)) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                checkedState = it
                viewModel.toggleInstrumentType(type)
            }
        )
        Text(type)
    }
}

@Composable
fun InstrumentItemView(instrument: Instrument) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = instrument.name,
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = instrument.ticker,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Type: ${instrument.instrument_type}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Current Price: ${instrument.current_price}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "Previous Price: ${instrument.previous_price}",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(text = instrument.description)
        }
    }
}
