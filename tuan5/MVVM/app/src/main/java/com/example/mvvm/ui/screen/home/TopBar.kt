package com.example.mvvm.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mvvm.R

@Composable
fun CustomTopBar(
    title: String,
    onBackClick: () -> Unit,
    onAddClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nút quay lại
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier.size(40.dp)
            )
        }

        // Tiêu đề ở giữa
        Text(
            text = title,
            color = Color(0xFF2196F3),
            style = MaterialTheme.typography.h4
        )

        // Nút thêm task
        if (onAddClick != null) {
            IconButton(
                onClick = onAddClick,
                modifier = Modifier.size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    modifier = Modifier.size(40.dp)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(48.dp)) // giữ cân đối bố cục
        }
    }
}
