package com.example.truth_or_dare.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.truth_or_dare.database.Question
import com.example.truth_or_dare.models.Player

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Player>?
) {
    val adapter = recyclerView.adapter as PlayerListAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("questionListData")
fun bindQuestionRecyclerView(
    recyclerView: RecyclerView,
    data: List<Question>?
) {
    val adapter = recyclerView.adapter as QuestionListAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}
