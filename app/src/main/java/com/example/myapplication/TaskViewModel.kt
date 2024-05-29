package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: MutableStateFlow<List<Task>> get() = _taskList// variables: mutable list of tasks state variable
    // function: add method, delete method,

    fun addTask(task: Task) {
        viewModelScope.launch {
            _taskList.update { currentList -> currentList + task }

        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            _taskList.update { currentList -> currentList - task }
        }
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            _taskList.value = _taskList.value.map{
                if (it == task) it.copy(isDone = !it.isDone) else it
            }
        }
    }
}