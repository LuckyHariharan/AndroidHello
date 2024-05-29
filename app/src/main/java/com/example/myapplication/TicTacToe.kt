package com.example.myapplication

data class Board(
    val cells: MutableList<MutableList<String>> = MutableList(4) { MutableList(4) { "" } }
)
