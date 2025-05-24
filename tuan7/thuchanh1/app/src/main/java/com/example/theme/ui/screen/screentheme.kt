package com.example.theme.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeSettingScreen(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    onApplyClick: () -> Unit
) {
    val themeColors = listOf(
        Color(0xFF21E1F3),
        Color.Magenta,
        Color.Black)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Setting", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Choosing the right theme sets the tone and personality of your app",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            themeColors.forEach { color ->
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color)
                        .border(
                            width = if (color == selectedColor) 4.dp else 2.dp,
                            color = if (color == selectedColor) Color.White else Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onColorSelected(color) }
                )

            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onApplyClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .width(150.dp)
                .height(48.dp)
        ) {
            Text("Apply", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeSettingScreenPreview() {
    ThemeSettingScreen(
        selectedColor = Color(0xFF2196F3),
        onColorSelected = {},
        onApplyClick = {}
    )
}
