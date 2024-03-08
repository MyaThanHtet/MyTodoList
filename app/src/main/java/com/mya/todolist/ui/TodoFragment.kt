package com.mya.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mya.todolist.databinding.FragmentTodoBinding
import com.mya.todolist.model.TodoResult
import com.mya.todolist.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoFragment : Fragment() {
    private lateinit var binding: FragmentTodoBinding
    private val viewModel: TodoViewModel by viewModels()
    private val adapter by lazy { TodoAdapter(requireContext(), arrayListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        // Fetch Todolist data from ViewModel
        viewModel.getTodoList()
        // Observe changes in Todolist data
        viewModel.todo.observe(viewLifecycleOwner) { response ->

            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {

                            // Set data to the adapter if response is successful and data is not empty
                            adapter.setData(it as MutableList<TodoResult>)

                        } else {
                            Toast.makeText(requireContext(), "No Response", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                is NetworkResult.Error -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {
                            Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_LONG)
                                .show()

                            // Set data to the adapter even if there's a network error
                            adapter.setData(it as MutableList<TodoResult>)


                        } else {
                            Toast.makeText(requireContext(), "No Response", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                is NetworkResult.Loading -> {
                    // Do nothing if loading
                }

            }
        }
    }
}




