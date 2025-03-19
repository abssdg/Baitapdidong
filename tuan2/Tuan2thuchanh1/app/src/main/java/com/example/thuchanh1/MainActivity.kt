package com.example.thuchanh1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnKiemTra = findViewById<Button>(R.id.btnKiemTra)
        var edtTuoi = findViewById<EditText>(R.id.edtTuoi)
        var edtHoTen = findViewById<EditText>(R.id.edtTen)

        btnKiemTra.setOnClickListener {
            var tuoi = edtTuoi.text.toString().toInt()
            var hoTen = edtHoTen.text.toString()

            when (tuoi) {
                in 66..500 -> Toast.makeText(this, "Xin chào $hoTen, bạn là người già!", Toast.LENGTH_SHORT).show()
                in 19..65 -> Toast.makeText(this, "Xin chào $hoTen, bạn là người lơn!", Toast.LENGTH_SHORT).show()
                in 6..18 -> Toast.makeText(this, "Xin chào $hoTen, bạn là trẻ em!", Toast.LENGTH_SHORT).show()
                in 0..5 -> Toast.makeText(this, "Xin chào $hoTen, bạn là em bé!", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Xin chào $hoTen, tuổi không hợp lệ!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}