package com.example.gilman_arief_firmansyah.ui.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gilman_arief_firmansyah.data.repository.TodoRepository
import com.example.gilman_arief_firmansyah.helper.Resource
import com.example.gilman_arief_firmansyah.viewmodel.TodoDetailViewModel


@Composable
fun TodoDetailScreen(
    todoId: Int,
    viewModel: TodoDetailViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoDetailViewModel(TodoRepository()) as T
        }
    })
) {
    LaunchedEffect(Unit) {
        viewModel.fetchTodoDetail(todoId)
    }

    val state = viewModel.todo.value

    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Todo ID: ${state.data?.id}", fontSize = 18.sp)
                Text("Title: ${state.data?.title}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("Completed: ${state.data?.completed}", fontSize = 16.sp)
            }
        }
        is Resource.Error -> {
            Text(
                text = "Error: ${state.message}",
                modifier = Modifier.padding(16.dp),
                color = Color.Red
            )
        }
    }
}
