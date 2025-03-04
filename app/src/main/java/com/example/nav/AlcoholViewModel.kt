package com.example.nav

class AlcoholViewModel : MainViewModel() {
    init {
        fetchCocktails(CocktailAPI.retrofitService::getAlcoholicCocktails)
    }
}
