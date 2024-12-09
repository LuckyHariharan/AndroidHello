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
import com.example.myapplication.Instrument
import com.example.myapplication.InstrumentViewModel

@Composable
fun InstrumentScreen(viewModel: InstrumentViewModel) {
    val instruments by viewModel.filteredInstruments.collectAsState(initial = emptyList<Instrument>())
    var query by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.background, // Background color for the screen
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search Field
            OutlinedTextField(
                value = query,
                onValueChange = { newValue ->
                    query = newValue
                    viewModel.filterInstruments(query)
                },
                label = { Text("Search Instruments") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            // LazyColumn for the instrument list
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(instruments) { instrument ->
                    InstrumentCard(instrument)
                }
            }
        }
    }
}

@Composable
fun InstrumentCard(instrument: Instrument) {
    Surface(
        color = MaterialTheme.colorScheme.surface, // Surface background color
        shape = MaterialTheme.shapes.medium, // Rounded corners
        shadowElevation = 4.dp, // Elevation for shadow effect
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Row for Name and Ticker
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = instrument.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = instrument.ticker,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = instrument.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Current and Previous Prices
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Current Price",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Text(
                        text = "\$${instrument.current_price}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Green
                    )
                }
                Column {
                    Text(
                        text = "Previous Price",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Text(
                        text = "\$${instrument.previous_price}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red
                    )
                }
            }
        }
    }
}
