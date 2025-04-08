package com.example.thuchanh2.network

import com.example.thuchanh2.model.ApiResponse
import com.example.thuchanh2.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task> // API trả về danh sách Task trực tiếp

    @GET("task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): Task

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}

// Khởi tạo Retrofit
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/researchUTH/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}