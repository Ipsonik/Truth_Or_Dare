package com.example.truth_or_dare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.truth_or_dare.QuestionApplication
import com.example.truth_or_dare.databinding.FragmentHomePageBinding
import com.example.truth_or_dare.viewmodels.QuestionViewModel

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homePageFragment = this

    }

    fun play() {
        val action =
            HomePageFragmentDirections.actionHomePageFragmentToCreatePlayersDialogFragment()
        view?.findNavController()?.navigate(action)
    }
    fun question(){
        val action = HomePageFragmentDirections.actionHomePageFragmentToQuestionListFragment()
        view?.findNavController()?.navigate(action)
    }

}