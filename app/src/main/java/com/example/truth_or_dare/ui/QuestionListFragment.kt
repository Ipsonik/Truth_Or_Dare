package com.example.truth_or_dare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truth_or_dare.QuestionApplication
import com.example.truth_or_dare.R
import com.example.truth_or_dare.adapters.QuestionListAdapter
import com.example.truth_or_dare.databinding.FragmentQuestionListBinding
import com.example.truth_or_dare.viewmodels.QuestionViewModel


class QuestionListFragment : Fragment() {

    private lateinit var binding: FragmentQuestionListBinding
    private val viewModel: QuestionViewModel by activityViewModels {
        QuestionViewModel.QuestionViewModelFactory(
            (activity?.application as QuestionApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_question_list, container, false)
        val adapter = QuestionListAdapter(viewModel)
        binding.questionListFragment = this
        binding.apply {
            questionViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


