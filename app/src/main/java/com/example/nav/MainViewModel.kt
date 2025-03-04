package com.example.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nav.model.Cocktail
import com.example.nav.model.CocktailResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class MainViewModel : ViewModel() {
    private val _cocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val cocktails: StateFlow<List<Cocktail>> = _cocktails.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Cocktail>>(emptyList())
    val searchResults: StateFlow<List<Cocktail>> = _searchResults.asStateFlow()

    private val _currentTab = MutableStateFlow(Tab.Alc)
    val currentTab: StateFlow<Tab> = _currentTab.asStateFlow()

    fun onTabSelected(tab: Tab) {
        _currentTab.value = tab
        fetchCocktails(if (tab == Tab.Alc) CocktailAPI.retrofitService::getAlcoholicCocktails else CocktailAPI.retrofitService::getNonAlcoholicCocktails)
    }

    fun fetchCocktails(apiCall: suspend () -> CocktailResponse) {
        viewModelScope.launch {
            try {
                val response = apiCall()
                _cocktails.value = response.drinks ?: emptyList()
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
