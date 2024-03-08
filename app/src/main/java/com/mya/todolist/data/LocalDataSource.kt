package com.mya.todolist.data


import com.mya.todolist.data.database.dao.TodoDao
import com.mya.todolist.model.TodoResult
import javax.inject.Inject

// Local data source responsible for interacting with the TodoDatabase
class LocalDataSource @Inject constructor(
    private val todoDao: TodoDao
) {

    // Function to fetch all TodoItems from the database
    suspend fun getTodo(): List<TodoResult> {
        return todoDao.getAllTodos()
    }

    // Function to insert a list of TodoItems into the database
    suspend fun insertTodo(todo: List<TodoResult>) {
        todoDao.insertTodo(todo)
    }

    //Function to delete a list of TodoItems from local database
    suspend fun deleteAllTodo() {
        todoDao.deleteAllTodos()
    }
}