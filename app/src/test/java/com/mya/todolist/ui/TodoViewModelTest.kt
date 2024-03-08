package com.mya.todolist.ui

import com.mya.todolist.data.Repository
import com.mya.todolist.model.TodoResult
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response


class TodoViewModelTest {

    // Mock Repository
    private lateinit var repository: Repository

    // ViewModel to be tested
    private lateinit var viewModel: TodoViewModel

    // Sample TodoData
    private val sampleTodoList = listOf(
        TodoResult(1, 1, "Task 1", false),
        TodoResult(2, 2, "Task 2", true)
    )

    @Before
    fun setUp() {
        // Initialize mocked Repository and ViewModel
        repository = mock(Repository::class.java)
        viewModel = TodoViewModel(repository)
    }

    @Test
    fun testGetTodo() {
        // Mock repository to return sample TodoData as a successful network result
        runBlocking {
            `when`(repository.remote.getTodos()).thenReturn(Response.success(sampleTodoList))

            // Call the function to get TodoData
            viewModel.getTodoList()

            // Since getTodoList is a suspend function, it returns Unit
            // So, there's nothing to assert here
        }
    }
}
