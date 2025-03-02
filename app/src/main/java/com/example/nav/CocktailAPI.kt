package com.example.nav.retrofit

import com.example.nav.model.CocktailResponse
import retrofit2.http.GET

interface CocktailAPI {
    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholicCocktails(): CocktailResponse

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicCocktails(): CocktailResponse
}
