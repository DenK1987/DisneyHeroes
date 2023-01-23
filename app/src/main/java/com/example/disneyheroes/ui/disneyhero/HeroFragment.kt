package com.example.disneyheroes.ui.disneyhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.disneyheroes.R
import com.example.disneyheroes.databinding.FragmentHeroBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.models.InfoHero
import com.example.disneyheroes.repositories.SharedPreferencesRepository
import com.example.disneyheroes.ui.disneyhero.infoheroadapter.InfoHeroAdapter
import com.example.disneyheroes.ui.listdisneyheroes.ListHeroesFragment
import com.example.disneyheroes.utils.loadUrl
import com.example.disneyheroes.utils.navigationFragments

class HeroFragment : Fragment() {

    private lateinit var binding: FragmentHeroBinding

    private val viewModel: HeroViewModel by viewModels()

    private var isFavoriteHero = false

    private lateinit var currentHero: DisneyHero

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharPrefRep = SharedPreferencesRepository(requireContext())

        binding.buttonBack.setOnClickListener {
            navigationFragments(parentFragmentManager, ListHeroesFragment())
        }

        binding.buttonLike.setOnClickListener {
            if (!currentHero.isFavorite) {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_30)
                sharPrefRep.setFavoriteHero(currentHero.id.toString(), true)
                viewModel.selectFavoriteHero(currentHero)
            } else {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                sharPrefRep.setFavoriteHero(currentHero.id.toString(), false)
                viewModel.selectFavoriteHero(currentHero)
            }
        }

        viewModel.oneHero.observe(viewLifecycleOwner) {
            currentHero = it
            binding.textNameHero.text = it.name
            binding.imageHero.loadUrl(it.imageUrl)
            setList(it.listInfo)
            val flagFavorite = sharPrefRep.getFavoriteHero(currentHero.id.toString())
            isFavoriteHero = if (isFavoriteHero != flagFavorite) {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_30)
                true
            } else {
                binding.buttonLike.setImageResource(R.drawable.ic_baseline_favorite_border_30)
                false
            }
        }

        arguments?.getString(ID_HERO)?.let {
            viewModel.getInfoOneHero(it)
        }
    }

    private fun setList(list: ArrayList<InfoHero>) {
        binding.listCategoriesHero.run {
            if (adapter == null) {
                adapter = InfoHeroAdapter()
                layoutManager = LinearLayoutManager(requireContext())
            }
            (adapter as? InfoHeroAdapter)?.submitList(list)
        }
    }

    companion object {
        private const val ID_HERO = "idHero"

        fun newInstance(id: String): HeroFragment {
            return HeroFragment().apply {

                arguments = bundleOf(ID_HERO to id)
            }
        }
    }
}
