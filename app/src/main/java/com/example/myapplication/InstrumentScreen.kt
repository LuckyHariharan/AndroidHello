package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel){
    val filteredInstruments by viewModel._filteredInstruments.collectAsState()
    var query by remember {
        mutableStateOf("")
    }
    // Build the screen layout
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)){
            OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = query, onValueChange ={ newQuery ->
                query = newQuery
                viewModel.filterInstruments(query)
            }, label= { Text("Search Bar") } )
            
            Spacer(modifier = Modifier.padding(4.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredInstruments){instrument ->
                    InstrumentCard(instrument = instrument)
                }

            }
        }
    }
}

@Composable
fun InstrumentCard(instrument: InstrumentItem){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp), shape = MaterialTheme.shapes.medium, shadowElevation = 8.dp) {
        Column(Modifier.fillMaxWidth()) {
            Row(Modifier.fillMaxWidth().padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = instrument.name, color= Color.Black, style = MaterialTheme.typography.bodyLarge)
                Text(text = instrument.ticker, color=Color.DarkGray, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.padding(4.dp))

            Row(Modifier.fillMaxWidth()){
                Column(modifier=Modifier.padding(8.dp)) {

                    Text(text = "Previous Price", color= Color.Black, style = MaterialTheme.typography.bodyMedium)
                    Text(text = instrument.previous_price.toString(), color= Color.Red, style = MaterialTheme.typography.bodyMedium)

                }
                Column(modifier=Modifier.padding(8.dp)) {

                    Text(text = "Previous Price", color= Color.Black, style = MaterialTheme.typography.bodyMedium)
                    Text(text = instrument.previous_price.toString(), color= Color.Green, style = MaterialTheme.typography.bodyMedium)

                }            }
        }

    }
}