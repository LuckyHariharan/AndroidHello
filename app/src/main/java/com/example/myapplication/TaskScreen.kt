package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskScreen(taskViewModel: TaskViewModel = viewModel()) {
    // get private variables from viewmodel
    val taskList by taskViewModel.taskList.collectAsState()

    var taskTitle by remember { mutableStateOf("") }
    // lazy column to display list of tasks
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Tasks",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text("Task Title") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = {
                    if (taskTitle.isNotBlank()) {
                        taskViewModel.addTask(Task(taskTitle = taskTitle, isDone = false))
                        taskTitle = ""
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Create Task")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(taskList) { task ->
                TaskItem(
                    task = task,
                    onDelete = { taskViewModel.deleteTask(it) },
                    onComplete = { taskViewModel.completeTask(it) }
                )
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onDelete: (Task) -> Unit, onComplete: (Task) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.taskTitle, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = if (task.isDone) "Completed" else "Pending",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = { onComplete(task) }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Complete Task",
                    tint = if (task.isDone) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onDelete(task) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task"
                )
            }
        }
    }
}