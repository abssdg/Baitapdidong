package com.example.bai1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai1.model.Task
import com.example.bai1.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccess) {
                    _tasks.value = response.data // Lấy danh sách từ "data"
                } else {
                    _errorMessage.value = "Lỗi API: ${response.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Không thể tải dữ liệu: ${e.message}"
            }
        }
    }
}
