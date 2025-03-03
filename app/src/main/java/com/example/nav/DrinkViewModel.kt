package com.example.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nav.model.Cocktail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrinkViewModel : ViewModel() {
    private val _alcoholCocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val alcoholCocktails: StateFlow<List<Cocktail>> = _alcoholCocktails.asStateFlow()

    private val _nonAlcoholCocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val nonAlcoholCocktails: StateFlow<List<Cocktail>> = _nonAlcoholCocktails.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Cocktail>>(emptyList())
    val searchResults: StateFlow<List<Cocktail>> = _searchResults.asStateFlow()

    init {
        fetchAlcoholicCocktails()
        fetchNonAlcoholicCocktails()
    }

    private fun fetchAlcoholicCocktails() {
        viewModelScope.launch {
            try {
                val response = CocktailAPI.retrofitService.getAlcoholicCocktails()
                _alcoholCocktails.value = response.drinks ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchNonAlcoholicCocktails() {
        viewModelScope.launch {
            try {
                val response = CocktailAPI.retrofitService.getNonAlcoholicCocktails()
                _nonAlcoholCocktails.value = response.drinks ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchCocktails(name: String) {
        viewModelScope.launch {
            try {
                val response = CocktailAPI.retrofitService.searchCocktails(name)
                _searchResults.value = response.drinks ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

