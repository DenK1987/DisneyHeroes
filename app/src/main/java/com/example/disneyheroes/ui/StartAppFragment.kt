package com.example.disneyheroes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.disneyheroes.databinding.FragmentStartAppBinding
import com.example.disneyheroes.ui.listdisneyheroes.ListHeroesFragment
import com.example.disneyheroes.utils.navigationFragments
import com.example.disneyheroes.utils.navigationFragmentsAndAddToBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetStarted.setOnClickListener {
            navigationFragmentsAndAddToBackStack(parentFragmentManager, ListHeroesFragment())
        }
    }
}