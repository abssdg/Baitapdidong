package com.example.mvvm.data.model

import androidx.compose.ui.graphics.Color

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean = false
)

val pastelColors = listOf(
    Color(0xFFBBDEFB), // xanh nhạt
    Color(0xFFF8BBD0), // hồng nhạt
    Color(0xFFDCE775), // vàng nhạt
    Color(0xFFFFF9C4), // kem nhạt
    Color(0xFFE1BEE7)  // tím nhạt
)

