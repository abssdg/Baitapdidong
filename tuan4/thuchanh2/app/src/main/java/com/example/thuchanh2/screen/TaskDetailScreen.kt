package com.example.thuchanh2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.thuchanh2.model.Task
import com.example.thuchanh2.viewmodel.TaskViewModel

@Composable
fun DetailScreen(navController: NavController, viewModel: TaskViewModel, taskId: Int) {
    var task by remember { mutableStateOf<Task?>(null) } // Task có thể null

    LaunchedEffect(taskId) {
        viewModel.fetchTaskDetail(taskId)
    }

    // Lắng nghe trạng thái từ ViewModel
    task = viewModel.taskDetail

    if (task == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(task!!.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Mô tả: ${task!!.description}", fontSize = 16.sp)
        Text("Trạng thái: ${task!!.status}", fontSize = 16.sp)
        Text("Độ ưu tiên: ${task!!.priority}", fontSize = 16.sp)
        Text("Hạn chót: ${task!!.dueDate}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Công việc con:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        LazyColumn {
            items(task!!.subtasks) { subtask ->
                Text("- ${subtask.title}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.deleteTask(task!!.id) { navController.popBackStack() }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Xóa Task", color = MaterialTheme.colorScheme.onError)
        }
    }
}
