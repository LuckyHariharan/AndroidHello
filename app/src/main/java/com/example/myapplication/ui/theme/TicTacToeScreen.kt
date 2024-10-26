package com.example.myapplication.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TicTacToeScreen(viewModel: TicTacToeViewModel) {
    val board by viewModel.ticTacToeBoard.observeAsState(emptyList())
    val currentPlayer by viewModel.currentPlayer.observeAsState("X")
    val winner = viewModel.checkWinner()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the current player or winner
        Text(
            text = winner?.let { "Winner: $it!" } ?: "Current Player: $currentPlayer",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        // 3x3 Grid
        Column {
            for (row in 0..2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (col in 0..2) {
                        val index = row * 3 + col
                        TicTacToeCell(
                            value = board.getOrNull(index) ?: "",
                            onClick = { viewModel.makeMove(index) },
                            modifier = Modifier.weight(1f)
                        )
                        if (col != 2) {
                            Spacer(modifier = Modifier.width(4.dp)) // Space between cells
                        }
                    }
                }
                if (row != 2) {
                    Spacer(modifier = Modifier.height(4.dp)) // Space between rows
                    Divider(color = Color.Black, thickness = 1.dp) // Horizontal Divider
                }
            }
        }

        // Reset and Undo Buttons
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { viewModel.resetBoard() }) {
                Text(text = "Reset")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.undoMove() }) {
                Text(text = "Undo")
            }
        }
    }
}

@Composable
fun TicTacToeCell(value: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { if (value.isEmpty()) onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            color = if (value == "X") Color.Red else Color.Blue
        )
    }
}
