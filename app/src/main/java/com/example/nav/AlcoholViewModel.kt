package com.example.nav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nav.model.Cocktail
import com.example.nav.retrofit.CocktailAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlcoholViewModel : ViewModel() {
    private val _cocktails = MutableStateFlow<List<Cocktail>>(emptyList())
    val cocktails: StateFlow<List<Cocktail>> = _cocktails

    private val api = Retrofit.Builder()
        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CocktailAPI::class.java)

    init {
        fetchAlcoholicCocktails()
    }

    private fun fetchAlcoholicCocktails() {
        viewModelScope.launch {
            val response = api.getAlcoholicCocktails()
            _cocktails.value = response.drinks ?: emptyList()
        }
    }
}
