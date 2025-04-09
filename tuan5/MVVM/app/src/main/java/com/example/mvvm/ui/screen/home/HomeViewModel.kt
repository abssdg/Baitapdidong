package com.example.mvvm.ui.screen.home


import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.model.Task
import com.example.mvvm.data.repository.TaskRepository
import com.example.mvvm.data.repository.TaskRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository = TaskRepositoryImpl()
) : ViewModel() {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { tasks ->
                _taskList.value = tasks
            }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.insertTask(Task(title = title, description = description))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun handleAddTask(
        title: String,
        description: String,
        onSuccess: () -> Unit
    ) {
        if (title.isNotBlank() && description.isNotBlank()) {
            addTask(title, description)
            onSuccess()
        }
    }
}
