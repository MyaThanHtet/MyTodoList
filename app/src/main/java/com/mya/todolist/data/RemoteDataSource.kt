package com.mya.todolist.data

import com.mya.todolist.data.network.TodoApi
import com.mya.todolist.model.TodoResult
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val todoApi: TodoApi) {

    //getTodoList
    suspend fun getTodos(): Response<List<TodoResult>> {
        return todoApi.getTodo()
    }

}