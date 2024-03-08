package com.mya.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mya.todolist.data.database.dao.TodoDao
import com.mya.todolist.model.TodoResult

@Database(
    entities = [TodoResult::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {

    // Define an abstract function to get the DAO (Data Access Object)
    abstract fun todoDao(): TodoDao

}

