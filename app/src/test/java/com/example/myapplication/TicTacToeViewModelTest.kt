package com.example.myapplication

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TicTacToeViewModelTest {

    private lateinit var viewModel: TicTacToeViewModel

       @Before
       fun setup(){
           viewModel = TicTacToeViewModel()
       }

    @Test
    fun `makeMove on empty cell changes value`(){
        val initPlayer = viewModel.currentPlayer.value
        viewModel.makeMove(0,0)
        assertEquals(initPlayer,viewModel.board.value.cells[0][0])
    }
    @Test
    fun `makeMove on occupied cell does not change cell value`() {
        viewModel.makeMove(0, 0) // First move
        val currentPlayer = viewModel.currentPlayer.value
        viewModel.makeMove(0, 0) // Try to move in the same cell
        assertNotEquals(currentPlayer, viewModel.board.value.cells[0][0])
    }
}
