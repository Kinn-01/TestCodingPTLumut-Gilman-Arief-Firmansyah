package com.example.gilman_arief_firmansyah.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gilman_arief_firmansyah.data.repository.TodoRepository
import com.example.gilman_arief_firmansyah.helper.Resource
import com.example.gilman_arief_firmansyah.viewmodel.TodoListViewModel


@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoListViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoListViewModel(TodoRepository()) as T
        }
    })
) {
    val state = viewModel.todos.value

    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            LazyColumn {
                items(state.data ?: emptyList()) { todo ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("detail/${todo.id}") }
                            .padding(16.dp)
                    ) {
                        Text(text = todo.title, fontSize = 16.sp)
                        Divider()
                    }
                }
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
