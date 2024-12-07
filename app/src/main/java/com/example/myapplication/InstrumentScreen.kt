package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier


@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    val filteredInstrumentList by viewModel.filteredInstrumentList.collectAsState()
    val filters = listOf("all", "etf", "stock", "crypto")
    Column {
    // setup screen with layout
    Row {
        filters.forEach { filter -> InstrumentFilterButtons(input = filter, onFilterClick = {viewModel.filterInstruments(filter)}) }

    }
    // InstrumentFilterButtons(input)
    Spacer(modifier = Modifier.fillMaxWidth(16f))

    LazyColumn {
        items(filteredInstrumentList) { instrument ->
            InstrumentCard(instrument)
        }
    }
    //
    }
}

@Composable
fun InstrumentCard(instrument: Instrument) {
    Column {
        Card {
            Text(text = instrument.name)
        }
    }
}

@Composable
fun InstrumentFilterButtons(input: String, onFilterClick: (String)-> Unit) {
   OutlinedButton(onClick = { onFilterClick(input) }) {
       Text(text=input)
   }
}