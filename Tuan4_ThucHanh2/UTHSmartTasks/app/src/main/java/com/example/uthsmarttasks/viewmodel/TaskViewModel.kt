package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.api.RetrofitInstance
import com.example.uthsmarttasks.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccessful) {
                    val taskResponse = response.body()
                    if(taskResponse?.isSuccess == true){
                        _tasks.value = taskResponse.data
                    }                }
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }
        }
    }
}