package com.example.disneyheroes.ui.listdisneyheroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.FragmentListHeroesBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.ui.disneyhero.HeroFragment
import com.example.disneyheroes.ui.listdisneyheroes.adapter.FavoriteHeroesAdapter
import com.example.disneyheroes.ui.listdisneyheroes.adapter.HeroesAdapter
import com.example.disneyheroes.utils.navigationFragments
import com.example.disneyheroes.utils.navigationFragmentsAndAddToBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListHeroesFragment : Fragment() {

    private lateinit var binding: FragmentListHeroesBinding

    private val viewModel: ListHeroesViewModel by viewModels()

    private lateinit var recyclerFavoriteHeroes: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listHeroes.observe(viewLifecycleOwner) {
            setListHeroes(it)
        }
        viewModel.getListHeroes()

        binding.run {
            buttonMyHeroes.setOnClickListener {
                viewModel.listFavoriteHeroes.observe(viewLifecycleOwner) {
                    setListFavoriteHeroes(it)
                }
                viewModel.getListFavoriteHeroes()

                buttonMyHeroes.setImageResource(R.drawable.ic_baseline_favorite_30)
                buttonMyHeroes.setBackgroundResource(R.drawable.bg_image_button_checked)
                textMy.setTextColor(root.context.getColor(R.color.color_image_button_checked))
                binding.textAll.setTextColor(root.context.getColor(R.color.color_image_button_unchecked))
                binding.buttonAllHeroes.setBackgroundResource(R.drawable.bg_image_button_unchecked)
            }

            buttonAllHeroes.setOnClickListener {
                navigationFragments(parentFragmentManager, ListHeroesFragment())

                buttonAllHeroes.setBackgroundResource(R.drawable.bg_image_button_checked)
                textAll.setTextColor(root.context.getColor(R.color.color_image_button_checked))
                buttonMyHeroes.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                buttonMyHeroes.setBackgroundResource(R.drawable.bg_image_button_unchecked)
                textMy.setTextColor(root.context.getColor(R.color.color_image_button_unchecked))
            }
        }
    }

    private fun setListHeroes(listHeroes: List<DisneyHero>) {
        binding.listHeroes.run {
            if (adapter == null) {
                adapter = HeroesAdapter {
                    navigationFragmentsAndAddToBackStack(
                        parentFragmentManager,
                        HeroFragment.newInstance(it.id.toString())
                    )
                }
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
            (adapter as? HeroesAdapter)?.submitList(listHeroes)
        }
    }

    private fun setListFavoriteHeroes(listFavoriteHeroes: List<DisneyHero>) {
        recyclerFavoriteHeroes = binding.listHeroes
        recyclerFavoriteHeroes.run {
            adapter = FavoriteHeroesAdapter {
                navigationFragmentsAndAddToBackStack(
                    parentFragmentManager,
                    HeroFragment.newInstance(it.id.toString())
                )
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            (recyclerFavoriteHeroes.adapter as? FavoriteHeroesAdapter)?.setListFavoriteHero(
                listFavoriteHeroes
            )
        }
    }
}