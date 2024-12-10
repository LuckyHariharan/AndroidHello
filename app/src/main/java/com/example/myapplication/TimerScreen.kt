package com.example.myapplication

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerScreen(viewModel: TimerViewModel) {
    // grab timer state variable from viewmodel
    val timerSeconds = viewModel.timerSeconds.collectAsState()
    // LazyColumn have infinite rows
    // Row of a signle timer, arranged end
    LazyColumn {

        itemsIndexed((1..1000).toList()) { index, _ ->
            val timeState = viewModel.timerSeconds.collectAsState() // Observe state
            val time = timeState.value[index]?.value ?: 0L// Default to 0 if not initialized

            TimerRow(index = index, time = time) {
                viewModel.activateTimer(index)
            }
        }
    }

}

@Composable
fun TimerRow(
    index: Int, time: Long, onActivate: () -> Unit
) {
    // Trigger the activation callback when this composable enters the viewport
    LaunchedEffect(Unit) {
        onActivate()
    }

    // Display the timer's index and time
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
//        Text(
//            text = "Timer $index:",
//            modifier = Modifier.weight(1f),
//            style = MaterialTheme.typography.bodyMedium
//        )
        Text(
            text = "${time}s", style = MaterialTheme.typography.bodyMedium
        )
    }
}
