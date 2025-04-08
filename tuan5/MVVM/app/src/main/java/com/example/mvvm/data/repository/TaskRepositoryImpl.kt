package com.example.mvvm.data.repository

import com.example.mvvm.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskRepositoryImpl : TaskRepository {

    // Danh sách chứa nhiều "list" của các task (ở đây dùng một list đơn giản).
    private val tasks = mutableListOf<Task>()
    // StateFlow phát hành danh sách hiện tại của các task
    private val _tasksFlow = MutableStateFlow<List<Task>>(emptyList())
    override fun getAllTasks(): Flow<List<Task>> = _tasksFlow.asStateFlow()

    override suspend fun insertTask(task: Task) {
        // Giả định id tự tăng theo số lượng hiện có
        val newTask = task.copy(id = tasks.size + 1)
        tasks.add(newTask)
        _tasksFlow.value = tasks.toList()
    }

    override suspend fun updateTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
            _tasksFlow.value = tasks.toList()
        }
    }

    override suspend fun deleteTask(task: Task) {
        tasks.removeAll { it.id == task.id }
        _tasksFlow.value = tasks.toList()
    }
}
