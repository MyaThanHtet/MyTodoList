package com.mya.todolist.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mya.todolist.model.TodoResult


@Dao
interface TodoDao {

    // Function to insert all todos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(popular: List<TodoResult>)


    // Function to retrieve all todos
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<TodoResult>

    // Function to delete all todos
    @Query("DELETE FROM todos")
    suspend fun deleteAllTodos()
}