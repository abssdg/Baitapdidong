package com.example.thuchanh2.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.thuchanh2.viewmodel.TaskViewModel
import com.example.thuchanh2.model.Task
import com.example.thuchanh2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, viewModel: TaskViewModel) {
    LaunchedEffect(Unit) { viewModel.fetchTasks() }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("SmartTasks", fontSize = 22.sp) })

        if (viewModel.tasks.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Không có công việc nào", fontSize = 18.sp)
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.tasks) { task ->
                    TaskItem(task, onClick = { navController.navigate("detail/${task.id}") })
                }
            }
        }

        FloatingActionButton(
            onClick = { /* Thêm task mới */ },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task")
        }
    }
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(task.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(task.description, fontSize = 16.sp)
        }
    }
}