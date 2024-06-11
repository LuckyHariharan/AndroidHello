package com.example.myapplication

class InstrumentList : ArrayList<Instrument>()
// 1 generate data class using alt + insert and pasting JSON
data class Instrument(
    val current_price: Double,
    val description: String,
    val id: String,
    val instrument_type: String,
    val name: String,
    val previous_price: Double,
    val ticker: String
)