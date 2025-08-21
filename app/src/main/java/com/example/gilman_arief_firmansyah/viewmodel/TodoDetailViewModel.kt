package com.example.gilman_arief_firmansyah.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gilman_arief_firmansyah.data.repository.TodoRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.gilman_arief_firmansyah.data.model.Todo
import com.example.gilman_arief_firmansyah.helper.Resource
import kotlinx.coroutines.launch

class TodoDetailViewModel(private val repo: TodoRepository) : ViewModel() {
    private val _todo = mutableStateOf<Resource<Todo>>(Resource.Loading())
    val todo: State<Resource<Todo>> = _todo

    fun fetchTodoDetail(id: Int) {
        viewModelScope.launch {
            _todo.value = Resource.Loading()
            try {
                val data = repo.getTodoDetail(id)
                _todo.value = Resource.Success(data)
            } catch (e: Exception) {
                _todo.value = Resource.Error(e.message ?: "Error")
            }
        }
    }
}
