package com.example.gilman_arief_firmansyah.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gilman_arief_firmansyah.data.repository.TodoRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.gilman_arief_firmansyah.data.model.Todo
import com.example.gilman_arief_firmansyah.helper.Resource
import kotlinx.coroutines.launch

class TodoListViewModel(private val repo: TodoRepository) : ViewModel() {
    private val _todos = mutableStateOf<Resource<List<Todo>>>(Resource.Loading())
    val todos: State<Resource<List<Todo>>> = _todos

    init { fetchTodos() }

    fun fetchTodos() {
        viewModelScope.launch {
            _todos.value = Resource.Loading()
            try {
                val data = repo.getTodos()
                _todos.value = Resource.Success(data)
            } catch (e: Exception) {
                _todos.value = Resource.Error(e.message ?: "Error")
            }
        }
    }
}
