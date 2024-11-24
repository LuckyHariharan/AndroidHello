package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    // grab instrument, filter instrument
    val filters = listOf("all", "crypto", "stock", "etf")
    val instruments by viewModel.filteredInstruments.observeAsState()
    if (instruments == null) {
        return
    }

    Column {
        Row {
            filters.forEach { filter ->
                FilterButton(type = filter, onFilterClick = { viewModel.filterInstruments(filter )})
            }
        }
//        }
//        Spacer(modifier = Modifier.padding(16.dp))
        // lazy column instruments, display name
        LazyColumn {
            items(instruments!!) { instrument ->
                InstrumentCard(instrument)
            }
        }
    }
}

@Composable
fun FilterButton(type: String, onFilterClick: (String)->Unit) {
    OutlinedButton(onClick = { onFilterClick(type) }) {
        Text(type)
    }
}


@Composable
fun InstrumentCard(instrument: Instrument) {
    Text(text = instrument.name)
}
// stock, etf, crypto, and all