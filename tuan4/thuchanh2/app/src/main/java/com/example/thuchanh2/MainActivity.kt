package com.example.bai1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.thuchanh2.ui.theme.Thuchanh2Theme
import com.example.thuchanh2.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Thuchanh2Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Intro) }

    Surface(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
            is Screen.Intro -> IntroScreen(onContinueClicked = { currentScreen = Screen.Home })
            is Screen.Home -> HomeScreen(onItemClicked = { currentScreen = Screen.Details(it) })
            is Screen.Details -> DetailScreen(item = (currentScreen as Screen.Details).item, onBack = { currentScreen = Screen.Home })
        }
    }
}

sealed class Screen {
    object Intro : Screen()
    object Home : Screen()
    data class Details(val item: String) : Screen()
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
                containerColor = Color(0xFF2196F3), // Màu xanh da trời
                contentColor = Color.White // Màu chữ trắng
            )
        )
        {
            Text("I'm ready", fontSize = 16.sp)
        }
    }
}


@Composable
fun HomeScreen(onItemClicked: (String) -> Unit) {
    val items = listOf(
        "Display" to listOf("Text", "Image"),
        "Input" to listOf("TextField", "PasswordField"),
        "Layout" to listOf("Column", "Row")
    )

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            Text(
                "UI Components List",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(items) { (category, components) ->
            Text(
                text = category,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            components.forEach { item ->
                ItemCard(name = item,
                    onClick = { onItemClicked(item) })
            }
        }
    }
}

@Composable
fun ItemCard(name: String, onClick: () -> Unit) {
    Surface(
        color = Color(0xFFD0E8FF),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .background(Color(0xFFD0E8FF)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(text = "Click to see details", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun DetailScreen(item: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow1),
                contentDescription = "Back",
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onBack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Detail ",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "Details for: $item",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    Thuchanh2Theme {
        MyApp()
    }
}