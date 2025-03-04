package com.example.nav

class NonAlcoholViewModel : MainViewModel() {
    init {
        fetchCocktails(CocktailAPI.retrofitService::getNonAlcoholicCocktails)
    }
}
