package com.example.mvvm.data.model

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean = false
)

val pastelColors = listOf(
    Color(0xFFBBDEFB),
    Color(0xFFF8BBD0),
    Color(0xFFDCE775),
    Color(0xFFFFF9C4),
    Color(0xFFE1BEE7)
)

