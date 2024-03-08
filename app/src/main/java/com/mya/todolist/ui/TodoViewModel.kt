package com.mya.todolist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mya.todolist.data.Repository
import com.mya.todolist.model.TodoResult
import com.mya.todolist.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    // LiveData for holding Todolist data along with network response status
    var todo: MutableLiveData<NetworkResult<List<TodoResult>?>> = MutableLiveData()

    // Function to fetch Todolist data
    fun getTodoList() = viewModelScope.launch {

        // Perform the Todolist data retrieval in a coroutine
        getTodoListSafeCall()
    }

    // Function to safely fetch Todolist data
    private suspend fun getTodoListSafeCall() {

        // Delete local data before fetching data from the network
        repository.local.deleteAllTodo()

        // Set loading state to LiveData
        todo.value = NetworkResult.Loading()

        try {
            // Make network call to fetch Todolist from remote data source
            val response = repository.remote.getTodos()
            if (response.isSuccessful) {

                // If successful response, insert data into local database and update LiveData
                response.body()?.let { repository.local.insertTodo(it) }
                todo.value = NetworkResult.Success(response.body())

            } else {

                // If network call fails, fetch data from local database and update LiveData with error
                val responseRoom = repository.local.getTodo()
                todo.value = NetworkResult.Error("No Internet Connection", responseRoom)

            }

        } catch (e: Exception) {

            // If an exception occurs during network call, fetch data from local database and update LiveData with error
            val responseRoom = repository.local.getTodo()
            todo.value = NetworkResult.Error("${e.message}", responseRoom)

        }

    }
}
