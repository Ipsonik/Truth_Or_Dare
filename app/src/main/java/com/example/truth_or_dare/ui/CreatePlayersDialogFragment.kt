package com.example.truth_or_dare.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.truth_or_dare.QuestionApplication
import com.example.truth_or_dare.R
import com.example.truth_or_dare.adapters.PlayerListAdapter
import com.example.truth_or_dare.databinding.FragmentCreatePlayersDialogBinding
import com.example.truth_or_dare.viewmodels.QuestionViewModel

class CreatePlayersDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCreatePlayersDialogBinding
    private val viewModel: QuestionViewModel by activityViewModels {
        QuestionViewModel.QuestionViewModelFactory(
            (activity?.application as QuestionApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_players_dialog,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.createPlayersDialogFragment = this
        binding.recyclerView.apply {
            adapter = PlayerListAdapter()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val background = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(background,20)
        dialog?.window?.setBackgroundDrawable(inset)
        dialog?.window?.setLayout(
            (LinearLayout.LayoutParams.MATCH_PARENT),
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

    }

    fun play() {
        val action =
            CreatePlayersDialogFragmentDirections.actionCreatePlayersDialogFragmentToGameFragment()
        findNavController().navigate(action)
    }

    fun error() {
        binding.playerNameInput.error = "Nie dodano graczy"

    }

}