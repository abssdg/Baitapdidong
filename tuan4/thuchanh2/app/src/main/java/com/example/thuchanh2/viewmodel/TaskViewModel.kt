package com.example.thuchanh2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thuchanh2.model.Task
import com.example.thuchanh2.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.thuchanh2.network.RetrofitInstance.api

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf<List<Task>>(emptyList())
    var taskDetail by mutableStateOf<Task?>(null)

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                tasks = api.getTasks()
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi gọi API: ${e.message}")
            }
        }
    }

    fun fetchTaskDetail(id: Int) {
        viewModelScope.launch {
            try {
                taskDetail = api.getTaskDetail(id)
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi lấy chi tiết task: ${e.message}")
            }
        }
    }

    fun deleteTask(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                api.deleteTask(id)
                tasks = tasks.filter { it.id != id } // Xóa task khỏi danh sách
                onSuccess()
            } catch (e: Exception) {
                Log.e("TaskViewModel", "Lỗi khi xóa task: ${e.message}")
            }
        }
    }
}