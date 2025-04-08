package com.example.thuchanh2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import com.example.thuchanh2.screen.DetailScreen
import com.example.thuchanh2.screen.TaskListScreen
import androidx.compose.runtime.Composable
import com.example.thuchanh2.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel = remember { TaskViewModel() }
                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "tasklist") {
        composable("tasklist") { TaskListScreen(navController, viewModel) }
        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                DetailScreen(navController, viewModel, taskId)
            }
        }
    }

}