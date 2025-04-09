package com.example.mvvm.ui.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvm.data.model.Task
import com.example.mvvm.ui.screen.addtask.AddTaskScreen
import com.example.mvvm.ui.screen.home.HomeScreen
import com.example.mvvm.ui.screen.home.HomeViewModel

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                onAddTaskClick = { navController.navigate("addTask") },
                onTaskClick = { task: Task ->
                }
            )
        }
        composable("addTask") {
            AddTaskScreen(
                onAddTask = { title, description ->
                    homeViewModel.addTask(title, description)
                },
                onBack = { navController.popBackStack() },
                navController = navController,
                HomeViewModel = homeViewModel
            )
        }
    }
}
