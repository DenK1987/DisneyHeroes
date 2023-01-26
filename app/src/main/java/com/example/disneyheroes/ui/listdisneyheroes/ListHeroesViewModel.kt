package com.example.disneyheroes.ui.listdisneyheroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.repositories.DisneyHeroesRepository
import com.example.disneyheroes.utils.toListDisneyHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHeroesViewModel @Inject constructor(
    private val repository: DisneyHeroesRepository
)  : ViewModel() {

    private val _listHeroes = MutableLiveData<List<DisneyHero>>()
    val listHeroes: LiveData<List<DisneyHero>> = _listHeroes

    private val _listFavoriteHeroes = MutableLiveData<List<DisneyHero>>()
    val listFavoriteHeroes: LiveData<List<DisneyHero>> = _listFavoriteHeroes

    fun getListHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDisneyHeroes()
            if (response.isSuccessful) {
                _listHeroes.postValue(
                    response.body()?.data?.toListDisneyHero() ?: emptyList()
                )
            } else {
                response.errorBody()
            }
        }
    }

    fun getListFavoriteHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            _listFavoriteHeroes.postValue(repository.getFavoriteDisneyHeroes())
        }
    }
}