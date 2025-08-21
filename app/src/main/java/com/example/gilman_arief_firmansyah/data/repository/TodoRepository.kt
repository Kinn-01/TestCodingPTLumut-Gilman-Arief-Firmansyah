package com.example.gilman_arief_firmansyah.data.repository

import com.example.gilman_arief_firmansyah.data.network.ApiClient

class TodoRepository {
    private val api = ApiClient.api

    suspend fun getTodos() = api.getTodos()
    suspend fun getTodoDetail(id: Int) = api.getTodoDetail(id)
}