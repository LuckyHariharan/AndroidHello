package com.example.myapplication.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {
    // create our state variables to represent the state of the game
    val ticTacToeBoard = MutableLiveData<List<String>>(List(9)
    {
        ""
    })
    var currentPlayer = MutableLiveData<String>("X")
    val moveHistory = MutableLiveData<List<Int>>(emptyList())

    init {
        resetBoard()
    }

    // create our method implementations for game logic
    // add X or O onto board, reset board function, undo
    fun resetBoard() {
        ticTacToeBoard.value = List(9) { "" }
        currentPlayer.value = "X"
        moveHistory.value = emptyList()
    }

    fun makeMove(position: Int) {
        val currentBoard = ticTacToeBoard.value ?: return
        if (currentBoard[position].isEmpty()) {
            val updatedBoard = currentBoard.toMutableList()
            updatedBoard[position] = currentPlayer.value ?: "X"
            ticTacToeBoard.value = updatedBoard
            currentPlayer.value = if (currentPlayer.value == "X") "O" else "X"
            val history = moveHistory.value?.toMutableList() ?: mutableListOf()
            history.add(position)
            moveHistory.value = history
        }
    }

    fun undoMove() {
        val history = moveHistory.value?.toMutableList() ?: return
        val currentBoard = ticTacToeBoard.value?.toMutableList() ?: return

        if (history.isNotEmpty()) {
            val lastMove = history.removeAt(history.size - 1)
            currentBoard[lastMove] = ""

            ticTacToeBoard.value = currentBoard
            moveHistory.value = history

            currentPlayer.value = if (currentPlayer.value == "X") "O" else "X"
        }
    }

    fun checkWinner(): String? {
        val board = ticTacToeBoard.value ?: return null

        val winningCombinations = mutableListOf<List<Int>>()
        for (i in 0..2) {
            // 0-2, 3-5, 6-8
            winningCombinations.add(listOf(i * 3, i * 3 + 1, i * 3 + 2))
        }

        for (i in 0..2) {
            winningCombinations.add(listOf(i, i + 3, i + 6))
        }
        winningCombinations.add(listOf(0, 4, 8))
        winningCombinations.add(listOf(2, 4, 6))

        for (combo in winningCombinations) {
            val (a, b, c) = combo
            if (board[a].isNotEmpty() && board[a] == board[b] && board[b] == board[c]) {
                return board[a]
            }
        }

        return null
    }
}