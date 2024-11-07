package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    // Observe input and result state from the ViewModel
    val input = viewModel.input.value
    val result = viewModel.result.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Display input and result
        InputBox(input, result)

        Spacer(modifier = Modifier.height(16.dp))

        // Display buttons for numbers and operators
        Buttons(viewModel)
    }
}

@Composable
fun InputBox(input: String, result: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = input,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Text(
            text = result,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
    }
}

@Composable
fun Buttons(viewModel: CalculatorViewModel) {
    val buttonRows = listOf(
        listOf("7", "8", "9", "/"),
        listOf("4", "5", "6", "*"),
        listOf("1", "2", "3", "-"),
        listOf("0", "C", "=", "+")
    )

    // Constrain the button area to occupy a fixed height
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp), // Limit the height of the button area
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        buttonRows.forEach { row ->
            ButtonRow(row, viewModel)
        }
    }
}

@Composable
fun ButtonRow(symbols: List<String>, viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        symbols.forEach { symbol ->
            CalculatorButton(symbol = symbol, viewModel = viewModel)
        }
    }
}

@Composable
fun CalculatorButton(symbol: String, viewModel: CalculatorViewModel) {
    Button(
        onClick = {
            when (symbol) {
                "=" -> viewModel.calculate()
                "C" -> viewModel.clear()
                else -> viewModel.onButtonClick(symbol)
            }
        },
        modifier = Modifier
            .height(64.dp) // Set fixed height to control button size
    ) {
        Text(text = symbol)
    }
}
