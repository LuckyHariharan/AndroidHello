package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel: ViewModel() {
    // Use Board class to encapsulate board state
    private val _board = MutableStateFlow(Board())
    val board = _board.asStateFlow()

    // Current player turn
    private val _currentPlayer = MutableStateFlow("X")
    val currentPlayer = _currentPlayer.asStateFlow()

    // Win state
    private val _winState = MutableStateFlow(false)
    val winState = _winState.asStateFlow()

    fun makeMove(row: Int, col: Int) {
        if (_board.value.cells[row][col].isEmpty() && !_winState.value) {
            _board.value.cells[row][col] = _currentPlayer.value
            if (checkWin(row, col)) {
                _winState.value = true
            } else {
                togglePlayer()
            }
        }
    }

    private fun togglePlayer() {
        _currentPlayer.value = if (_currentPlayer.value == "X") "O" else "X"
    }

    private fun checkWin(row: Int, col: Int): Boolean {
        val player = _currentPlayer.value
        val rowWin = _board.value.cells[row].all { it == player }
        val colWin = _board.value.cells.all { it[col] == player }
        val diagonalWin = if (row == col) _board.value.cells.indices.all { _board.value.cells[it][it] == player } else false
        val antiDiagonalWin = if (row + col == 3) _board.value.cells.indices.all { _board.value.cells[it][3 - it] == player } else false

        return rowWin || colWin || diagonalWin || antiDiagonalWin
    }
}
