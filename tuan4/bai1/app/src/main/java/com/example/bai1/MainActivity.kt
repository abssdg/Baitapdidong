package com.example.bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bai1.model.Screen
import com.example.bai1.model.Task
import com.example.bai1.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskApp()
        }
    }
}

@Composable
fun TaskApp(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Intro) }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
            is Screen.Intro -> IntroScreen(onContinueClicked = { currentScreen = Screen.Home })
            is Screen.Home -> TaskListScreen (tasks,
                onItemClicked = { task -> currentScreen = Screen.Details(task) }, // Truyền Task thay vì String
                onBack = { currentScreen = Screen.Intro })
            is Screen.Details -> DetailScreen(
                task = (currentScreen as Screen.Details).task,
                onBack = { currentScreen = Screen.Home },
                onBackToIntro = { currentScreen = Screen.Intro })
        }
    }
}


@Composable
fun TaskListScreen(tasks: List<Task>, onItemClicked: (Task) -> Unit, onBack: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
                horizontalArrangement = Arrangement.Center // Căn giữa theo chiều ngang
            ){
                Button(
                    onClick = { onBack() },
                    modifier = Modifier
                        .padding(start = 8.dp, top = 16.dp, bottom = 16.dp)
                        .size(50.dp), // Đảm bảo kích thước đủ lớn
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent // Không có màu nền
                    ),
                    contentPadding = PaddingValues(0.dp), // Xóa padding mặc định
                    shape = ShapeDefaults.Small // Giữ nguyên hình dạng ảnh
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp) // Điều chỉnh kích thước ảnh
                    )
                }

                    Text(
                    text = "LazyColumn",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier
                        .weight(1f) // Giúp Text chiếm phần còn lại mà không đẩy ảnh
                        .padding(bottom = 32.dp, top = 32.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        items(tasks) { task ->
            TaskItem(task = task, onClick = { onItemClicked(task) }) // Truyền toàn bộ Task vào
        }
    }
}


@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    val backgroundColor = when (task.priority.lowercase()) {
        "high" -> Color.Red.copy(alpha = 0.2f)  // Màu đỏ nhạt cho độ ưu tiên cao
        "medium" -> Color.Yellow.copy(alpha = 0.2f)  // Màu vàng nhạt cho độ ưu tiên trung bình
        "low" -> Color.Green.copy(alpha = 0.2f)  // Màu xanh nhạt cho độ ưu tiên thấp
        else -> Color.LightGray.copy(alpha = 0.2f)  // Màu xám nhạt nếu không có mức ưu tiên rõ ràng
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc
            horizontalArrangement = Arrangement.SpaceBetween // Cách đều các thành phần
        ) {
            Column(modifier = Modifier.weight(1f)) { // Cột chứa nội dung chính
                Text(task.title, style = MaterialTheme.typography.titleLarge)
                Text(task.description, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Độ ưu tiên: ${task.priority}",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Button(
                onClick = { onClick() },
                modifier = Modifier
                    .size(50.dp), // Đảm bảo kích thước nút
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Xóa nền nút
                contentPadding = PaddingValues(0.dp) // Loại bỏ padding mặc định
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backblack),
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp) // Giữ nguyên kích thước ảnh
                )
            }
        }
    }

}


@Composable
fun IntroScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Jetpack Compose", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = onContinueClicked,
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 32.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color(0xFF2196F3), // Màu xanh da trời
                contentColor = androidx.compose.ui.graphics.Color.White // Màu chữ trắng
            )
        )
        {
            Text("PUSH", fontSize = 16.sp)
        }
    }
}

@Composable
fun DetailScreen(task: Task, onBack: () -> Unit ,onBackToIntro: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { onBack() },
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, bottom = 16.dp)
                    .size(50.dp), // Đảm bảo kích thước đủ lớn
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent // Không có màu nền
                ),
                contentPadding = PaddingValues(0.dp), // Xóa padding mặc định
                shape = ShapeDefaults.Small // Giữ nguyên hình dạng ảnh
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp) // Điều chỉnh kích thước ảnh
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Detail",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text("Tiêu đề: ${task.title}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Mô tả: ${task.description}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Trạng thái: ${task.status}", fontSize = 16.sp, color = Color.Gray)
        Text("Độ ưu tiên: ${task.priority}", fontSize = 16.sp, color = Color.Gray)
        Text("Hạn chót: ${task.dueDate}", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Các công việc con:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        task.subtasks.forEach { subtask ->
            Text("- ${subtask.title} (${if (subtask.isCompleted) "Hoàn thành" else "Chưa xong"})", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = { onBackToIntro() },
            modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color(0xFF2196F3), // Màu xanh da trời
                contentColor = androidx.compose.ui.graphics.Color.White // Màu chữ trắng
            )
        ) {
            Text("BACK TO ROOT", fontSize = 16.sp)
        }
    }
}

@Preview
@Composable
fun TaskAppPreview() {
    TaskApp()
}