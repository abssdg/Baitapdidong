package com.example.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

@Composable
fun ProfileInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Text(
        text = label,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start, // Căn phải chữ
        modifier = Modifier
            .fillMaxWidth() // Cho phép căn theo chiều rộng
            .padding(bottom = 4.dp)
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}


@Composable
fun HomeScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    var name by remember { mutableStateOf(user?.displayName ?: "Unknown") }
    var email by remember { mutableStateOf(user?.email ?: "No Email") }
    var photoUrl by remember { mutableStateOf(user?.photoUrl?.toString() ?: "") }

    var dateOfBirth by remember { mutableStateOf("") }
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            dateOfBirth = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.back), // Thay bằng hình ảnh của bạn
                    contentDescription = "Back Button",
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.width(110.dp))
            Text("Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
        }

        Image(
                painter = rememberAsyncImagePainter(photoUrl), // Hiển thị ảnh đại diện từ Google
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Name
        ProfileInputField(label = "Name", value = name, onValueChange = { name = it })

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        ProfileInputField(label = "Email", value = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Date of Birth",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start, // Căn phải chữ
            modifier = Modifier
                .fillMaxWidth() // Cho phép căn theo chiều rộng
                .padding(bottom = 4.dp)
        )
        // Nhập Ngày Sinh
        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { datePickerDialog.show() }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Select Date")
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {
                auth.signOut()
                navController.navigate("login") { popUpTo("login") { inclusive = true } }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
