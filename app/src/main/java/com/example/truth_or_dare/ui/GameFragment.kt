package com.example.truth_or_dare.ui

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.truth_or_dare.QuestionApplication
import com.example.truth_or_dare.R
import com.example.truth_or_dare.database.Question
import com.example.truth_or_dare.databinding.FragmentGameBinding
import com.example.truth_or_dare.viewmodels.QuestionViewModel
import android.view.View as View


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    private val viewModel: QuestionViewModel by activityViewModels {
        QuestionViewModel.QuestionViewModelFactory(
            (activity?.application as QuestionApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.questionViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val observer = androidx.lifecycle.Observer<List<Question>> {
            viewModel.roundHandling()
        }
        viewModel.allQuestions.observe(this.viewLifecycleOwner, observer)
        binding.gameFragment = this

        val animDrawable = binding.rootLayout.background as AnimationDrawable
        animDrawable.apply {
            setEnterFadeDuration(10)
            setExitFadeDuration(5000)
            start()
        }

        return binding.root
    }

    fun restart() {
        val action =
            GameFragmentDirections.actionGameFragmentToCreatePlayersDialogFragment()
        findNavController().navigate(action)
    }
}