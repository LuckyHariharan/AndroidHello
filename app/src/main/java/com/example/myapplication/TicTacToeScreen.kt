package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicTacToeScreen(ticTacToeViewModel: TicTacToeViewModel = viewModel()) {
    val board by ticTacToeViewModel.board.collectAsState()
    val currentPlayer by ticTacToeViewModel.currentPlayer.collectAsState()
    val winState by ticTacToeViewModel.winState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Player: $currentPlayer", modifier = Modifier.padding(16.dp))
        if (winState) {
            Text("Player $currentPlayer wins!", modifier = Modifier.padding(16.dp))
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(board.cells.flatten()) { index, cell ->
                val row = index / 4
                val col = index % 4
                Button(
                    onClick = { ticTacToeViewModel.makeMove(row, col) },
                    modifier = Modifier.size(60.dp)
                ) {
                    Text(text = if (cell.isNotEmpty()) cell else " ")
                }
            }
        }
    }
}
