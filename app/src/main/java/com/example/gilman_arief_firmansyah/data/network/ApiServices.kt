package com.example.gilman_arief_firmansyah.data.network

import com.example.gilman_arief_firmansyah.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodoDetail(@Path("id") id: Int): Todo
}