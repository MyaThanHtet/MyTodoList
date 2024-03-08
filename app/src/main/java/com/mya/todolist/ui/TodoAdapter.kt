package com.mya.todolist.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mya.todolist.R
import com.mya.todolist.databinding.LayoutTodoItemBinding
import com.mya.todolist.model.TodoResult

class TodoAdapter(
    private val context: Context,
    private var todoList: MutableList<TodoResult>
) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = todoList[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    // Update the data set and notify RecyclerView of changes.
    fun setData(result: MutableList<TodoResult>) {
        todoList = result
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutTodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoItem: TodoResult) {
            binding.textViewTitle.text = todoItem.title

            // Set status text and icon based on completion state
            if (todoItem.completed) {
                binding.statusTv.text = context.getString(R.string.completed)
                binding.stateImg.setImageResource(R.drawable.check)
            } else {
                binding.statusTv.text = context.getString(R.string.incomplete)
                binding.stateImg.setImageResource(R.drawable.caution)


            }
        }
    }
}
