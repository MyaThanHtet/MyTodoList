package com.mya.todolist.data.network

import com.mya.todolist.model.TodoResult
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTodo(): Response<List<TodoResult>>
}