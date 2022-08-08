package com.example.truth_or_dare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.truth_or_dare.database.Question
import com.example.truth_or_dare.databinding.QuestionItemBinding
import com.example.truth_or_dare.viewmodels.QuestionViewModel

class QuestionListAdapter(private val viewModel: QuestionViewModel) :
    ListAdapter<Question, QuestionListAdapter.QuestionListViewHolder>(DiffCallback) {


    inner class QuestionListViewHolder(private var binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {

            binding.question = question
            binding.imageButton.setOnClickListener {
                viewModel.deleteQuestion(question)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListViewHolder {
        return QuestionListViewHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionListViewHolder, position: Int) {
        val question = getItem(position)

        holder.bind(question)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.content == newItem.content
        }

    }


}