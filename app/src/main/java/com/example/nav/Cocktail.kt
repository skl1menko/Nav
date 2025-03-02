package com.example.nav.model

import com.google.gson.annotations.SerializedName

data class Cocktail(
    @SerializedName("idDrink") val idDrink: String,
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strAlcoholic") val strAlcoholic: String,
    @SerializedName("strGlass") val strGlass: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strDrinkThumb") val strDrinkThumb: String?
)

data class CocktailResponse(
    @SerializedName("drinks") val drinks: List<Cocktail>?
)
