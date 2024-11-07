package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ezylang.evalex.Expression

class CalculatorViewModel : ViewModel() {

    // State variables for the current input and result
    val input = mutableStateOf("")
    val result = mutableStateOf("")

    // History of calculations: each entry is a Pair of expression and result
    val history = mutableStateOf<List<Pair<String, String>>>(emptyList())

    // Append a digit or operator to the current input
    fun onButtonClick(symbol: String) {
        input.value += symbol
    }

    // Evaluate the current expression and update history
    fun calculate() {
        try {
            val expressionString = input.value

            // Evaluate the expression using EvalEx
            val evalResult = Expression(expressionString).evaluate()
            val resultString = evalResult.numberValue.toPlainString()

            // Update result state
            result.value = resultString

            // Add to history (expression, result)
            history.value += Pair(expressionString, resultString)

        } catch (e: Exception) {
            result.value = "Error"
        }
    }

    // Clear the input and result (resets the current calculation only)
    fun clear() {
        input.value = ""
        result.value = ""
    }

    // Optional: Clear history as well
    fun clearHistory() {
        history.value = emptyList()
    }
}
