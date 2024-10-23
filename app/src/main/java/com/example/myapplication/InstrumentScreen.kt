package com.example.myapplication.instrument

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState

// Setup Instrument screen using composable
@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    // Get instruments LiveData from ViewModel\
    val instruments = viewModel.instruments.observeAsState()

    LazyColumn {
        // display instruments in a card
        items(instruments.value.orEmpty()) { instrument ->
            InstrumentCard(instrument)
        }
    }
}

@Composable
fun InstrumentCard(instrument: Instrument) {
    Card {
        Column {
            Text(text = instrument.ticker)
            Text(text = instrument.name)
            Text(text = instrument.description)
            Text(text = instrument.previous_price.toString())
            Text(text = instrument.current_price.toString())
        }
    }
}