package com.example.disneyheroes.ui.listdisneyheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.disneyheroes.databinding.ItemHeroBinding
import com.example.disneyheroes.models.DisneyHero

class HeroesAdapter(
    private val onClickHero: (hero: DisneyHero) -> Unit
) :
    ListAdapter<DisneyHero, HeroesViewHolder>(HeroesUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickHero(getItem(position))
        }
    }
}