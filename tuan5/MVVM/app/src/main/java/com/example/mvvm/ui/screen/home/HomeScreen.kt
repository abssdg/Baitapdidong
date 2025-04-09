package com.example.mvvm.ui.screen.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvm.R
import com.example.mvvm.data.model.Task
import com.example.mvvm.data.model.pastelColors

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    onAddTaskClick: () -> Unit,
    onTaskClick: (task: com.example.mvvm.data.model.Task) -> Unit
) {
    val tasks by viewModel.taskList.collectAsState()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "List",
                onBackClick = { navController.popBackStack() },
                onAddClick = { onAddTaskClick() },
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                onFabClick = onAddTaskClick
            )
        }

    ) { paddingValues ->
        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Chưa có công việc nào!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(tasks) { task ->
                    val colorIndex = (task.id.hashCode() and 0xFFFFFFF) % pastelColors.size
                    val bgColor = pastelColors[colorIndex]

                    TaskItem(
                        task = task,
                        onClick = { onTaskClick(task) },
                        onDeleteClick = { viewModel.deleteTask(task) },
                        backgroundColor = bgColor
                    )
                }
            }
        }


    }


}

@Composable
fun TaskItem(
    task: Task,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        backgroundColor = backgroundColor,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = task.description, fontSize = 18.sp)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Xoá")
            }
        }
    }
}





