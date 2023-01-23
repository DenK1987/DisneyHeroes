package com.example.disneyheroes.repositories

import com.example.disneyheroes.db.DataBase
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.network.Network
import com.example.disneyheroes.network.models.AllDisneyHeroesResponse
import com.example.disneyheroes.network.models.DisneyHeroResponse
import retrofit2.Response

class DisneyHeroesRepository {

    private val network = Network()

    suspend fun getDisneyHeroes(): Response<AllDisneyHeroesResponse> {
        return network.getDisneyHeroesApi().getDisneyHeroes()
    }

    suspend fun getImageDisneyHero(id: String): Response<DisneyHeroResponse> {
        return network.getDisneyHeroesApi().getImageDisneyHero(id)
    }

    suspend fun getFavoriteDisneyHeroes(): List<DisneyHero> {
        return DataBase.db.heroDao().getFavoriteDisneyHeroes()
    }

    suspend fun addHeroToFavorite(hero: DisneyHero) {
        DataBase.db.heroDao().addHero(hero)
    }

    suspend fun deleteHeroFromFavorite(hero: DisneyHero) {
        DataBase.db.heroDao().deleteHero(hero)
    }
}